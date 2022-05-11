package com.analytics.v1.app.command;

import com.analytics.v1.domain.point.PointInfo;
import com.analytics.v1.domain.point.PointTable;
import com.analytics.v1.domain.point.PointMeta;
import com.analytics.v1.infrastructure.common.exception.BadRequestException;
import com.analytics.v1.infrastructure.gateway.PointGateway;
import com.analytics.v1.infrastructure.gateway.TableGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:20 下午
 * @desc
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PointExe {
    private final PointGateway pointGateway;
    private final TableGateway tableGateway;

    public ArrayDeque<PointMeta> execute(List<Integer> ids) {
        List<PointInfo> points = this.pointGateway.findPoints(ids);

        List<PointTable> pointTables = processPointAndTable(points);

        ArrayDeque<PointMeta> pointMetas = processMeta(points, pointTables);

        return pointMetas;
    }

    private List<PointTable> processPointAndTable(List<PointInfo> points) {
        Set<String> tableNameSet = new HashSet<>(points.size());

        //拿到表名
        for (PointInfo point : points) {
            tableNameSet.add(point.getPointName());
        }
        //查询对应表
        List<PointTable> list = this.tableGateway.pointsTables(tableNameSet);

        HashSet<String> queryTableNameSet = new HashSet<>(list.size());
        for (PointTable pointTable : list) {
            queryTableNameSet.add(pointTable.getPointTableName());
        }
        //约束检查
        checkPointAndTable(tableNameSet, queryTableNameSet);

        return list;
    }

    private ArrayDeque<PointMeta> processMeta(List<PointInfo> points, List<PointTable> pointTables) {
        ArrayDeque<PointMeta> pointMetas = new ArrayDeque<>(pointTables.size());
        //维度，按表名分组
        Map<String, List<PointInfo>> collect = points.stream().collect(Collectors.groupingBy(PointInfo::getPointName));

        for (PointTable pointTable : pointTables) {
            if (collect.containsKey(pointTable.getPointTableName())) {
                List<PointInfo> pointInfos = collect.get(pointTable.getPointTableName());

                pointMetas.add(new PointMeta(pointTable, pointInfos));
            }
        }

        if (pointMetas.isEmpty()) {
            throw new BadRequestException("构造后的映射表为空");
        }
        return pointMetas;
    }

    private void checkPointAndTable(Set<String> tableNameSet, Set<String> queryTableNameSet) {
        log.info("tableNameSet:{}", tableNameSet);
        log.info("queryTableNameSet:{}", queryTableNameSet);

        if (tableNameSet.size() != queryTableNameSet.size()) {
            if (tableNameSet.size() > queryTableNameSet.size()) {
                tableNameSet.removeAll(queryTableNameSet);
                throw new BadRequestException("有维度没有找到对应维度表：" + tableNameSet);
            } else {
                queryTableNameSet.removeAll(tableNameSet);
                throw new BadRequestException("有维度没有找到对应维度表：" + queryTableNameSet);
            }
        }

        for (String s : queryTableNameSet) {
            if (!tableNameSet.contains(s)) {
                throw new BadRequestException("所传维度的维度表和数据库对不上：" + s);
            }
        }
    }
}

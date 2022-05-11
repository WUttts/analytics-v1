package com.analytics.v1.app.command;

import com.analytics.v1.domain.dim.DimInfo;
import com.analytics.v1.domain.dim.DimMeta;
import com.analytics.v1.domain.dim.DimTable;
import com.analytics.v1.infrastructure.common.exception.BadRequestException;
import com.analytics.v1.infrastructure.gateway.DimGateway;
import com.analytics.v1.infrastructure.gateway.TableGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:19 下午
 * @desc
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DimExe {
    private final DimGateway dimGateway;
    private final TableGateway tableGateway;

    public ArrayDeque<DimMeta> execute(List<Integer> ids) {
        //维度
        List<DimInfo> dims = this.dimGateway.findDim(ids);
        if (dims.isEmpty()) {
            return new ArrayDeque<>();
        }
        //获取维度对应的维度表
        List<DimTable> dimTables = processDimAndTable(dims);
        //根据维度和维度表建立映射关系
        ArrayDeque<DimMeta> dimMetas = processMeta(dims, dimTables);

        return dimMetas;
    }

    private List<DimTable> processDimAndTable(List<DimInfo> dims) {
        Set<String> tableNameSet = new HashSet<>(dims.size());

        //拿到表名
        for (DimInfo dim : dims) {
            tableNameSet.add(dim.getDimName());
        }
        //查询对应表
        List<DimTable> list = this.tableGateway.dimTables(tableNameSet);

        HashSet<String> queryTableNameSet = new HashSet<>(list.size());
        for (DimTable dimTable : list) {
            queryTableNameSet.add(dimTable.getDimTableName());
        }
        //约束检查
        checkDimAndTable(tableNameSet, queryTableNameSet);

        return list;
    }

    private ArrayDeque<DimMeta> processMeta(List<DimInfo> dims, List<DimTable> dimTables) {
        ArrayDeque<DimMeta> dimMetas = new ArrayDeque<>(dimTables.size());

        //维度，按表名分组
        Map<String, List<DimInfo>> collect = dims.stream().collect(Collectors.groupingBy(DimInfo::getDimName));

        for (DimTable dimTable : dimTables) {
            if (collect.containsKey(dimTable.getDimTableName())) {
                dimMetas.add(new DimMeta(dimTable, collect.get(dimTable.getDimTableName())));
            }
        }

        if (dimMetas.isEmpty()) {
            throw new BadRequestException("构造后的映射表为空");
        }
        return dimMetas;
    }

    private void checkDimAndTable(Set<String> tableNameSet, Set<String> queryTableNameSet) {
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

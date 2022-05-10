package com.analytics.v1.app.cmd;

import com.analytics.v1.domain.dim.DimInfo;
import com.analytics.v1.domain.dim.DimMeta;
import com.analytics.v1.domain.dim.DimTable;
import com.analytics.v1.infrastructure.common.exception.BadRequestException;
import com.analytics.v1.infrastructure.gateway.DimGateway;
import com.analytics.v1.infrastructure.gateway.TableGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:19 下午
 * @desc
 */
@Component
@RequiredArgsConstructor
public class DimExe {
    private final DimGateway dimGateway;
    private final TableGateway tableGateway;

    public DimMeta execute(List<Integer> ids) {
        //维度
        List<DimInfo> dims = this.dimGateway.findDim(ids);
        //获取维度对应的维度表
        List<DimTable> dimTables = processDimAndTable(dims);
        //根据维度和维度表建立映射关系
        Map<DimTable, List<DimInfo>> map = processMeta(dims, dimTables);

        return new DimMeta(map);
    }

    private List<DimTable> processDimAndTable(List<DimInfo> dims) {
        Set<String> tableNameSet = new HashSet<>(dims.size());

        //拿到表名
        for (DimInfo dim : dims) {
            tableNameSet.add(dim.getDimName());
        }
        //查询对应表
        List<DimTable> list = this.tableGateway.dimTables(tableNameSet);


        //获取查询后的表名
        HashSet<String> queryTableNameSet = new HashSet<>(list.size());
        for (DimTable dimTable : list) {
            queryTableNameSet.add(dimTable.getDimTableName());
        }

        //如果有某个维度没有找到对应的表
        if (tableNameSet.size() > queryTableNameSet.size()) {
            tableNameSet.removeAll(queryTableNameSet);
            throw new BadRequestException("没有找到：" + tableNameSet + "所对应的维度表");
        }

        return list;
    }

    private Map<DimTable, List<DimInfo>> processMeta(List<DimInfo> dims, List<DimTable> dimTables) {
        Map<DimTable, List<DimInfo>> hashMap = new HashMap<>(dimTables.size());
        //维度，按表名分组
        Map<String, List<DimInfo>> collect = dims.stream().collect(Collectors.groupingBy(DimInfo::getDimName));

        for (DimTable dimTable : dimTables) {
            if (collect.containsKey(dimTable.getDimTableName())) {
                hashMap.put(dimTable, collect.get(dimTable.getDimTableName()));
            }
        }

        if (hashMap.isEmpty()) {
            throw new BadRequestException("构造后的映射表为空");
        }
        return hashMap;
    }
}

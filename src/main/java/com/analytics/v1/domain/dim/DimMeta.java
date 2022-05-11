package com.analytics.v1.domain.dim;

import com.analytics.v1.domain.TableMeta;

import java.util.List;
import java.util.Map;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:21 下午
 * @desc
 */
public class DimMeta implements TableMeta {
    private final DimTable dimTable;
    private final List<DimInfo> dimInfos;

    public DimMeta(DimTable dimTable, List<DimInfo> dimInfos) {
        this.dimTable = dimTable;
        this.dimInfos = dimInfos;
    }

    public boolean isEmpty() {
        return null == dimInfos || dimInfos.isEmpty();
    }

    @Override
    public String tableName() {
        return dimTable.getDimTableName();
    }

    @Override
    public String countParttio() {
        return this.dimTable.getDimCountParttio();
    }

    @Override
    public int size() {
        return dimInfos.size();
    }
}

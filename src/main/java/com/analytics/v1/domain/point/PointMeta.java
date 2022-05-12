package com.analytics.v1.domain.point;

import com.analytics.v1.domain.TableMeta;

import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:22 下午
 * @desc
 */
public class PointMeta implements TableMeta {
    private final PointTable pointTable;
    private final List<PointInfo> pointInfos;

    public PointMeta(PointTable pointTable, List<PointInfo> pointInfos) {
        this.pointTable = pointTable;
        this.pointInfos = pointInfos;
    }

    @Override
    public String tableName() {
        return this.pointTable.getPointTableName();
    }

    @Override
    public String[] countParttio() {
        return this.pointTable.getPointCountParttio();
    }

    @Override
    public int size() {
        return this.pointInfos.size();
    }

    @Override
    public boolean check() {
        return this.size() > 0 && this.countParttio().length > 0;
    }

    public List<PointInfo> getPointInfos() {
        return pointInfos;
    }
}

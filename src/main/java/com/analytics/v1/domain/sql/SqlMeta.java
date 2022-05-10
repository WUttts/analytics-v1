package com.analytics.v1.domain.sql;

import com.analytics.v1.domain.dim.DimMeta;
import com.analytics.v1.domain.point.PointMeta;

/**
 * @author wutangsheng
 * @create 2022-05-10 1:49 下午
 * @desc
 */
public class SqlMeta {
    private final DimMeta dimMeta;
    private final PointMeta pointMeta;

    public SqlMeta(DimMeta dimMeta, PointMeta pointMeta) {
        this.dimMeta = dimMeta;
        this.pointMeta = pointMeta;
    }
}

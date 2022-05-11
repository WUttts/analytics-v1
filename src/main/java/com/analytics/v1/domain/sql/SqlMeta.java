package com.analytics.v1.domain.sql;

import com.analytics.v1.domain.dim.DimMeta;
import com.analytics.v1.domain.point.PointMeta;

import java.util.ArrayDeque;

/**
 * @author wutangsheng
 * @create 2022-05-10 1:49 下午
 * @desc
 */
public class SqlMeta {
    private final ArrayDeque<DimMeta> dimMetas;
    private final ArrayDeque<PointMeta> pointMetas;

    public SqlMeta(ArrayDeque<DimMeta> dimMetas, ArrayDeque<PointMeta> pointMetas) {
        this.dimMetas = dimMetas;
        this.pointMetas = pointMetas;
    }


}

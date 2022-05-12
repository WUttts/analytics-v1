package com.analytics.v1.domain.sql;

import com.analytics.v1.domain.dim.DimInfo;
import com.analytics.v1.domain.point.PointInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author townsend.wu
 * @create 2022-05-13 01:00
 * @desc
 */
@Getter
@ToString
@EqualsAndHashCode
public class SqlMeta {
    private final String sql;
    private final List<DimInfo> finalDims;
    private final List<PointInfo> finalPoints;

    public SqlMeta(String sql, List<DimInfo> finalDims, List<PointInfo> finalPoints) {
        this.sql = sql;
        this.finalDims = finalDims;
        this.finalPoints = finalPoints;
    }
}

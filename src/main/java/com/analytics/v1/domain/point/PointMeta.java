package com.analytics.v1.domain.point;

import java.util.List;
import java.util.Map;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:22 下午
 * @desc
 */
public class PointMeta {
    private Map<PointTable, List<PointInfo>> pointRelationalMap;

    public PointMeta(Map<PointTable, List<PointInfo>> pointRelationalMap) {
        this.pointRelationalMap = pointRelationalMap;
    }
}

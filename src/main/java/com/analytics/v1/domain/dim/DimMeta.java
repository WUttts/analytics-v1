package com.analytics.v1.domain.dim;

import java.util.List;
import java.util.Map;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:21 下午
 * @desc
 */
public class DimMeta {
    private Map<DimTable, List<DimInfo>> relationalMap;

    public DimMeta(Map<DimTable, List<DimInfo>> relationalMap) {
        this.relationalMap = relationalMap;
    }
}

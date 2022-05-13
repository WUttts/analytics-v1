package com.analytics.v1.domain.report;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author townsend.wu
 * @create 2022-05-12 23:30
 * @desc
 */
@Data
public class UnionData {
    private final List<Map<String, Object>> maps;

    private final Set<Alias> dimAlias;

    private final Set<Alias> pointAlias;


    public UnionData(List<Map<String, Object>> maps, Set<Alias> dimAlias, Set<Alias> pointAlias) {
        this.maps = maps;
        this.dimAlias = dimAlias;
        this.pointAlias = pointAlias;
    }

}

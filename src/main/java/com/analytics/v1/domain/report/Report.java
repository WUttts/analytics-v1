package com.analytics.v1.domain.report;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:28 下午
 * @desc
 */
@Data
public class Report {
    private final List<UnionData> unionDatas;

    public Report(List<UnionData> unionDatas) {
        this.unionDatas = unionDatas;
    }

    public Set<Alias> tableHead() {
        //维度头
        Set<Alias> dimAlias = unionDatas.get(0).getDimAlias();
        HashSet<Alias> hashSet = new HashSet<>();
        for (UnionData unionData : unionDatas) {
            hashSet.addAll(unionData.getPointAlias());
        }
        //合并
        dimAlias.addAll(hashSet);
        return dimAlias;
    }

    public List<Map<String, Object>> data() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UnionData unionData : this.unionDatas) {
            List<Map<String, Object>> maps = unionData.getMaps();
            list.addAll(maps);
        }
        return list;
    }

}

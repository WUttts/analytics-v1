package com.analytics.v1.domain.report;

import lombok.Data;

import java.util.List;

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
}

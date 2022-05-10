package com.analytics.v1.client.query;

import lombok.Data;

import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:32 下午
 * @desc
 */
@Data
public class ReportQuery {
    private Integer tableId;
    private String tableName;
    private ConfigInfo configInfo;

    @Data
    public static class ConfigInfo {
        private List<Integer> dims;
        private List<Integer> points;
    }
}

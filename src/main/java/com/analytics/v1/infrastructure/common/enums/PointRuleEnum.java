package com.analytics.v1.infrastructure.common.enums;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:54 下午
 * @desc
 */
public enum PointRuleEnum {
    COUNT(0, "count"),
    DISTINCT_COUNT(1, "distinct_count"),
    SUM(3, "sum");


    private final int status;
    private final String rule;

    PointRuleEnum(int status, String rule) {
        this.status = status;
        this.rule = rule;
    }

    public static PointRuleEnum convert(int status) {
        PointRuleEnum[] values = PointRuleEnum.values();
        for (PointRuleEnum value : values) {
            if (value.status == status) {
                return value;
            }
        }
        return null;
    }
}

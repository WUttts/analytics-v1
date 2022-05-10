package com.analytics.v1.infrastructure.common.enums;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:54 下午
 * @desc
 */
public enum DimRuleEnum {

    KEYWORDS(0, "取关键词"),

    CUSTOM(1, "自定义");

    private final int data;
    private final String desc;

    DimRuleEnum(int data, String desc) {
        this.data = data;
        this.desc = desc;
    }
}

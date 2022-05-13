package com.analytics.v1.domain.report;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author townsend.wu
 * @create 2022-05-13 19:08
 * @desc
 */
@Getter
@ToString
@EqualsAndHashCode
public class Alias {
    private final String column_name;
    private final String column_comment;

    public Alias(String columnName, String columnComment) {
        this.column_name = columnName;
        this.column_comment = columnComment;
    }
}

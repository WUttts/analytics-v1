package com.analytics.v1.domain.dim;

import lombok.Data;

/**
 * @author wutangsheng
 * @create 2022-05-10 1:55 下午
 * @desc
 */
@Data
public class DimTable {
    private Integer id;
    private String dimTableName;
    private String dimCountParttio;
}

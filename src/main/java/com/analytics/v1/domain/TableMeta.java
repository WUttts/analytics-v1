package com.analytics.v1.domain;

/**
 * @author wutangsheng
 * @create 2022-05-11 10:59 上午
 * @desc
 */
public interface TableMeta {
    String tableName();

    String[] countParttio();

    int size();

    boolean check();
}

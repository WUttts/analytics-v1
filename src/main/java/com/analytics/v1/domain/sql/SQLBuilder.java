package com.analytics.v1.domain.sql;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:21 下午
 * @desc
 */
public class SQLBuilder {
    private final SqlMeta sqlMeta;
    private LinkedList<String> sqlList;

    public SQLBuilder(SqlMeta sqlMeta) {
        this.sqlMeta = sqlMeta;
    }

    public void builder() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("select * from xx where xxx");
    }

    public LinkedList<String> getSQLs() {
        return sqlList;
    }
}

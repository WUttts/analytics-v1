package com.analytics.v1.app.command;

import com.analytics.v1.domain.report.Report;
import com.analytics.v1.domain.report.UnionData;
import com.analytics.v1.domain.sql.SqlMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:26 下午
 * @desc
 */
@Component
public class AnalyzeQueryExe {
    @Autowired
    @Qualifier("adbJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public UnionData execute(SqlMeta sqlMetas) {
        return new UnionData();
    }
}

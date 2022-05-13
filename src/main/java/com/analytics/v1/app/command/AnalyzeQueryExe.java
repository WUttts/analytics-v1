package com.analytics.v1.app.command;

import com.analytics.v1.domain.report.Report;
import com.analytics.v1.domain.report.UnionData;
import com.analytics.v1.domain.sql.SqlMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:26 下午
 * @desc
 */
@Component
public class AnalyzeQueryExe {
    @Autowired
    @Qualifier("adbJdbcTemplate")
    private JdbcTemplate adbTemplate;

    public UnionData execute(SqlMeta sqlMeta) {
        String sql = sqlMeta.getSql();
        List<Map<String, Object>> maps = adbTemplate.queryForList(sql);
        System.out.println(maps);
        return new UnionData();
    }
}

package com.analytics.v1.app.command;

import com.analytics.v1.domain.report.Report;
import com.analytics.v1.domain.report.UnionData;
import com.analytics.v1.domain.sql.SqlMeta;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:26 下午
 * @desc
 */
@Component
public class AnalyzeQueryExe {

    public UnionData execute(SqlMeta sqlMetas) {
        return new UnionData();
    }
}

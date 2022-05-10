package com.analytics.v1.app.cmd;

import com.analytics.v1.app.assembler.ReportAssembler;
import com.analytics.v1.client.query.ReportQuery;
import com.analytics.v1.client.vo.ReportVo;
import com.analytics.v1.domain.report.Report;
import org.springframework.stereotype.Component;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:26 下午
 * @desc
 */
@Component
public class AnalyzeQueryExe {

    public ReportVo execute(ReportQuery query) {
        return ReportAssembler.toVo(new Report());
    }
}

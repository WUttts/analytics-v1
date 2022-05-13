package com.analytics.v1.app.assembler;

import com.analytics.v1.client.vo.ReportVo;
import com.analytics.v1.domain.report.Report;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:30 下午
 * @desc
 */
public class ReportAssembler {
    public static ReportVo toVo(Report report) {
        ReportVo reportVo = new ReportVo();
        reportVo.setTableHead(report.tableHead());
        reportVo.setData(report.data());
        return reportVo;
    }
}

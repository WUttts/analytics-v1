package com.analytics.v1.app.service;

import com.analytics.v1.app.cmd.AnalyzeQueryExe;
import com.analytics.v1.app.cmd.DimExe;
import com.analytics.v1.app.cmd.PointExe;
import com.analytics.v1.client.api.IAnalyzesService;
import com.analytics.v1.client.query.ReportQuery;
import com.analytics.v1.client.vo.ReportVo;
import com.analytics.v1.domain.dim.DimMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:34 下午
 * @desc
 */
@Component
@RequiredArgsConstructor
public class AnalyzeServiceImpl implements IAnalyzesService {
    private final AnalyzeQueryExe analyzeQueryExe;
    private final DimExe dimExe;
    private final PointExe pointExe;


    @Override
    public ReportVo render(ReportQuery query) {
        DimMeta dimMeta = dimExe.execute(query.getConfigInfo().getDims());

        return analyzeQueryExe.execute(query);
    }
}

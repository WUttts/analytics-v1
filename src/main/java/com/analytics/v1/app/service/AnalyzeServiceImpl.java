package com.analytics.v1.app.service;

import com.analytics.v1.app.assembler.ReportAssembler;
import com.analytics.v1.app.command.AnalyzeQueryExe;
import com.analytics.v1.app.command.DimExe;
import com.analytics.v1.app.command.PointExe;
import com.analytics.v1.client.api.IAnalyzesService;
import com.analytics.v1.client.query.ReportQuery;
import com.analytics.v1.client.vo.ReportVo;
import com.analytics.v1.domain.dim.DimMeta;
import com.analytics.v1.domain.point.PointMeta;
import com.analytics.v1.domain.report.Report;
import com.analytics.v1.domain.sql.SQLBuilder;
import com.analytics.v1.domain.sql.SqlMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;

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
        ArrayDeque<DimMeta> dimMetas = dimExe.execute(query.getConfigInfo().getDims());

        ArrayDeque<PointMeta> pointMetas = pointExe.execute(query.getConfigInfo().getPoints());

        SqlMeta sqlMeta = new SqlMeta(dimMetas, pointMetas);

        SQLBuilder sqlBuilder = new SQLBuilder(sqlMeta);
        sqlBuilder.builder();

        Report report = analyzeQueryExe.execute(sqlBuilder.getSQLs());
        return ReportAssembler.toVo(report);
    }
}

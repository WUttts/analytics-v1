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
import com.analytics.v1.domain.report.UnionData;
import com.analytics.v1.domain.sql.SqlMeta;
import com.analytics.v1.domain.sql.SqlMetaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

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
        //校验、处理维度和指标
        LinkedList<DimMeta> dimMetas = dimExe.execute(query.getConfigInfo().getDims());
        ArrayDeque<PointMeta> pointMetas = pointExe.execute(query.getConfigInfo().getPoints());

        //构造sql
        SqlMetaBuilder sqlMetaBuilder = new SqlMetaBuilder(dimMetas, pointMetas);
        LinkedList<SqlMeta> sqlMetas = sqlMetaBuilder.builder();

        //执行sql查询，并预处理
        ArrayList<UnionData> unionDatas = new ArrayList<>(sqlMetas.size());
        for (SqlMeta sqlMeta : sqlMetas) {
            unionDatas.add(analyzeQueryExe.execute(sqlMeta));
        }

        //构造最终结果
        Report report = new Report(unionDatas);
        ReportVo reportVo = new ReportVo();
        reportVo.setSqlMetas(sqlMetas);
//        return ReportAssembler.toVo(report);
        return reportVo;
    }
}

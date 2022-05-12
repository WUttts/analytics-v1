package com.analytics.v1;

import com.analytics.v1.app.service.AnalyzeServiceImpl;
import com.analytics.v1.client.api.IAnalyzesService;
import com.analytics.v1.client.query.ReportQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
class AppTests {
    @Autowired
    IAnalyzesService iAnalyzeService;

    @Test
    void contextLoads() {
        ReportQuery reportQuery = new ReportQuery();
        reportQuery.setTableId(1);
        reportQuery.setTableName("掘金数据分析");
        ReportQuery.ConfigInfo configInfo = new ReportQuery.ConfigInfo();
        configInfo.setDims(Arrays.asList(3, 4));
        configInfo.setPoints(Arrays.asList(1,2));
        reportQuery.setConfigInfo(configInfo);
        iAnalyzeService.render(reportQuery);
    }

}

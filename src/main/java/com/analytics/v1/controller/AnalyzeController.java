package com.analytics.v1.controller;

import com.analytics.v1.client.api.IAnalyzesService;
import com.analytics.v1.client.query.ReportQuery;
import com.analytics.v1.client.vo.ReportVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:19 下午
 * @desc
 */
@RestController
@RequestMapping("/ana")
public class AnalyzeController {
    private final IAnalyzesService analyzesService;

    public AnalyzeController(IAnalyzesService analyzesService) {
        this.analyzesService = analyzesService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<ReportVo> analyze(@RequestBody ReportQuery query) {
        query.check();
        return ResponseEntity.ok(this.analyzesService.render(query));
    }
}

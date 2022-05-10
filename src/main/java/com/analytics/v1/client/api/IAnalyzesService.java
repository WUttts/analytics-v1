package com.analytics.v1.client.api;

import com.analytics.v1.client.query.ReportQuery;
import com.analytics.v1.client.vo.ReportVo;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:33 下午
 * @desc
 */
public interface IAnalyzesService {

    ReportVo render(ReportQuery query);

}

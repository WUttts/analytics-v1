package com.analytics.v1.client.vo;

import com.analytics.v1.domain.report.UnionData;
import com.analytics.v1.domain.sql.SqlMeta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:32 下午
 * @desc
 */
@Data
public class ReportVo implements Serializable {
    private List<SqlMeta> sqlMetas;
}

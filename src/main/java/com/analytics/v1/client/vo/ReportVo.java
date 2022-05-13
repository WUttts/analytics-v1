package com.analytics.v1.client.vo;

import com.analytics.v1.domain.report.Alias;
import com.analytics.v1.domain.report.UnionData;
import com.analytics.v1.domain.sql.SqlMeta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:32 下午
 * @desc
 */
@Data
public class ReportVo implements Serializable {
    private Set<Alias> tableHead;

    private List<Map<String, Object>> data;
}

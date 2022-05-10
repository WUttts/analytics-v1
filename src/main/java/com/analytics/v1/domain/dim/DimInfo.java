package com.analytics.v1.domain.dim;

import com.analytics.v1.infrastructure.common.enums.DimRuleEnum;
import lombok.Data;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:52 下午
 * @desc
 */
@Data
public class DimInfo {
    private Integer id;
    private String dimFieldName;
    private String dimDescribe;
    private DimRuleEnum dimRule;
    private String dimName;
}

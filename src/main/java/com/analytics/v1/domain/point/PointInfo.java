package com.analytics.v1.domain.point;

import com.analytics.v1.infrastructure.common.enums.PointRuleEnum;
import lombok.Data;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:52 下午
 * @desc
 */
@Data
public class PointInfo {
    private Integer id;
    private String pointFieldName;
    private String pointDescribe;
    private PointRuleEnum pointRule;
    private String pointName;
}

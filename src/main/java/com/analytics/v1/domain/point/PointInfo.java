package com.analytics.v1.domain.point;

import com.analytics.v1.infrastructure.common.enums.PointRuleEnum;
import com.analytics.v1.infrastructure.common.exception.BadRequestException;
import lombok.Data;
import org.springframework.util.StringUtils;

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

    public void check() {
        if (!StringUtils.hasText(pointFieldName)) {
            throw new BadRequestException("指标：id:" + this.id + "的pointFieldName为空");
        }
        if (!StringUtils.hasText(pointDescribe)) {
            throw new BadRequestException("指标：id:" + this.id + "的pointDescribe为空");
        }

        if (!StringUtils.hasText(pointName)) {
            throw new BadRequestException("指标：id:" + this.id + "的pointName为空");
        }
        if (pointRule == null) {
            throw new BadRequestException("指标：id:" + this.id + ",没有找到对应规则");
        }
    }
}

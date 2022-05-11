package com.analytics.v1.domain.dim;

import com.analytics.v1.infrastructure.common.enums.DimRuleEnum;
import com.analytics.v1.infrastructure.common.exception.BadRequestException;
import lombok.Data;
import org.springframework.util.StringUtils;

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

    public void check() {
        if (!StringUtils.hasText(dimFieldName)) {
            throw new BadRequestException("维度：id:" + this.id + "的dimFieldName为空");
        }
        if (!StringUtils.hasText(dimDescribe)) {
            throw new BadRequestException("维度：id:" + this.id + "的dimDescribe为空");
        }
        if (!StringUtils.hasText(dimName)) {
            throw new BadRequestException("维度：id:" + this.id + "的dimName为空");
        }
        if (dimRule == null) {
            throw new BadRequestException("维度：id:" + this.id + ",没有找到对应规则");
        }
    }
}

package com.analytics.v1.domain.point;

import com.analytics.v1.infrastructure.common.exception.BadRequestException;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author wutangsheng
 * @create 2022-05-10 1:55 下午
 * @desc
 */
@Data
public class PointTable {
    private Integer id;
    private String pointTableName;
    private String pointCountParttio;

    public void check() {
        if (!StringUtils.hasText(pointTableName)){
            throw new BadRequestException("指标表：id:"+this.id +"的pointTableName为空");
        }
        if (!StringUtils.hasText(pointCountParttio)){
            throw new BadRequestException("指标表：id:"+this.id +"的pointCountParttio为空!,请填写统计粒度！！");
        }
    }
}

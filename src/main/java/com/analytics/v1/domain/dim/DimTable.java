package com.analytics.v1.domain.dim;

import com.analytics.v1.infrastructure.common.exception.BadRequestException;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author wutangsheng
 * @create 2022-05-10 1:55 下午
 * @desc
 */
@Data
public class DimTable {
    private Integer id;
    private String dimTableName;
    private String[] dimCountParttio;

    public void check() {
        if (!StringUtils.hasText(dimTableName)) {
            throw new BadRequestException("维度表：id:" + this.id + "的dimTableName为空");
        }
        if (dimCountParttio.length == 0) {
            throw new BadRequestException("维度表：id:" + this.id + "的dimCountParttio为空!,请填写统计粒度！！");
        }
    }
}

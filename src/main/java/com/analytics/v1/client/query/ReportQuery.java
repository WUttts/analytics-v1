package com.analytics.v1.client.query;

import com.analytics.v1.infrastructure.common.exception.BadRequestException;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:32 下午
 * @desc
 */
@Data
public class ReportQuery {
    private Integer tableId;
    private String tableName;
    private ConfigInfo configInfo;

    @Data
    public static class ConfigInfo {
        private List<Integer> dims;
        private List<Integer> points;
    }

    public void check() {
        if (tableId == null) {
            throw new BadRequestException("tableId为空");
        }
        if (!StringUtils.hasText(tableName)) {
            throw new BadRequestException("请设置表名");
        }
        if (configInfo == null) {
            throw new BadRequestException("缺少报表配置");
        }
        if (configInfo.points.isEmpty()) {
            throw new BadRequestException("选取的指标为空，请重新选取");
        }
    }
}

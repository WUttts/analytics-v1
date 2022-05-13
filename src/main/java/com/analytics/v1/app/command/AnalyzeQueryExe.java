package com.analytics.v1.app.command;

import com.analytics.v1.domain.dim.DimInfo;
import com.analytics.v1.domain.point.PointInfo;
import com.analytics.v1.domain.report.Alias;
import com.analytics.v1.domain.report.Report;
import com.analytics.v1.domain.report.UnionData;
import com.analytics.v1.domain.sql.SqlMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wutangsheng
 * @create 2022-04-29 3:26 下午
 * @desc
 */
@Component
public class AnalyzeQueryExe {
    @Autowired
    @Qualifier("adbJdbcTemplate")
    private JdbcTemplate adbTemplate;

    public UnionData execute(SqlMeta sqlMeta) {
        //执行查询任务
        String sql = sqlMeta.getSql();
        List<Map<String, Object>> maps = this.adbTemplate.queryForList(sql);
        //构造别名
        Set<Alias> dimAlias = processDimAlias(sqlMeta, maps.get(0));
        Set<Alias> pointAlias = processPointAlias(sqlMeta, maps.get(0));

        return new UnionData(maps, dimAlias, pointAlias);
    }

    private Set<Alias> processDimAlias(SqlMeta sqlMeta, Map<String, Object> resultTemplate) {
        Set<Alias> aliases = new LinkedHashSet<>();
        for (DimInfo finalDim : sqlMeta.getFinalDims()) {
            if (resultTemplate.containsKey(finalDim.getDimFieldName())) {
                aliases.add(new Alias(finalDim.getDimFieldName(), finalDim.getDimDescribe()));
            }
        }

        return aliases;
    }

    private Set<Alias> processPointAlias(SqlMeta sqlMeta, Map<String, Object> resultTemplate) {
        Set<Alias> aliases = new LinkedHashSet<>();
        List<PointInfo> finalPoints = sqlMeta.getFinalPoints();
        for (PointInfo finalPoint : finalPoints) {
            if (resultTemplate.containsKey(finalPoint.getPointFieldName())) {
                aliases.add(new Alias(finalPoint.getPointFieldName(), finalPoint.getPointDescribe()));
            }
        }
        return aliases;
    }
}

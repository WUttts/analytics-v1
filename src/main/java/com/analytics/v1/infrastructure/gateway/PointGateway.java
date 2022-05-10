package com.analytics.v1.infrastructure.gateway;

import com.analytics.v1.domain.dim.DimInfo;
import com.analytics.v1.domain.point.PointInfo;
import com.analytics.v1.infrastructure.convert.DimConvert;
import com.analytics.v1.infrastructure.convert.PointConvert;
import com.analytics.v1.infrastructure.gateway.db.DimDo;
import com.analytics.v1.infrastructure.gateway.db.PointDo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:25 下午
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class PointGateway {
    private final JdbcTemplate jdbcTemplate;

    public List<PointInfo> findPoints(List<Integer> ids) {
        StringBuilder join = new StringBuilder();
        for (Integer id : ids) {
            join.append(id).append(",");
        }
        String substring = join.substring(0, join.length() - 1);
        String sql = String.format("select * from point_info where id in (%s)", substring);
        List<PointDo> pointDos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PointDo.class));
        return PointConvert.toDomain(pointDos);
    }
}

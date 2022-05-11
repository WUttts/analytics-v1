package com.analytics.v1.infrastructure.gateway;

import com.analytics.v1.domain.dim.DimInfo;
import com.analytics.v1.infrastructure.convert.DimConvert;
import com.analytics.v1.infrastructure.gateway.db.DimDo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:25 下午
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class DimGateway {
    private final JdbcTemplate jdbcTemplate;

    public List<DimInfo> findDim(List<Integer> ids) {
        StringBuilder join = new StringBuilder();
        for (Integer id : ids) {
            join.append(id).append(",");
        }
        String substring = join.substring(0, join.length() - 1);
        String sql = String.format("select * from dim_info where id in (%s)", substring);
        List<DimDo> dimDos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DimDo.class));

        if (dimDos.isEmpty()) {
            return new ArrayList<>();
        }

        return DimConvert.toDomain(dimDos);
    }

}

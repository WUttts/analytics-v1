package com.analytics.v1.infrastructure.gateway.db;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:34 下午
 * @desc
 */
@Data
public class DimDo implements RowMapper<DimDo> {
    private Integer id;
    private String dimFieldName;
    private String dimDescribe;
    private Integer dimRule;
    private String dimName;

    @Override
    public DimDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        DimDo dimDo = new DimDo();
        dimDo.setId(rs.getInt("id"));
        dimDo.setDimFieldName(rs.getString("dim_field_name"));
        dimDo.setDimDescribe(rs.getString("dim_describe"));
        dimDo.setDimName(rs.getString("dim_name"));
        dimDo.setDimRule(rs.getInt("dim_rule"));
        return dimDo;
    }
}

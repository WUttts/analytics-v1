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
public class DimTableDo implements RowMapper<DimTableDo> {
    private Integer id;
    private String dimTableName;
    private String dimCountParttio;

    @Override
    public DimTableDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        DimTableDo dimDo = new DimTableDo();
        dimDo.setId(rs.getInt("id"));
        dimDo.setDimTableName(rs.getString("dim_table_name"));
        dimDo.setDimCountParttio(rs.getString("dim_count_parttio"));
        return dimDo;
    }
}

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
public class PointTableDo implements RowMapper<PointTableDo> {
    private Integer id;
    private String pointTableName;
    private String pointCountParttio;

    @Override
    public PointTableDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        PointTableDo pointDo = new PointTableDo();
        pointDo.setId(rs.getInt("id"));
        pointDo.setPointTableName(rs.getString("point_table_name"));
        pointDo.setPointCountParttio(rs.getString("point_count_parttio"));
        return pointDo;
    }
}

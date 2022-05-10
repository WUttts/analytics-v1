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
public class PointDo implements RowMapper<PointDo> {
    private Integer id;
    private String pointFieldName;
    private String pointDescribe;
    private Integer pointRule;
    private String pointName;

    @Override
    public PointDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        PointDo pointDo = new PointDo();
        pointDo.setId(rs.getInt("id"));
        pointDo.setPointFieldName(rs.getString("point_field_name"));
        pointDo.setPointDescribe(rs.getString("point_describe"));
        pointDo.setPointName(rs.getString("point_name"));
        pointDo.setPointRule(rs.getInt("point_rule"));
        return pointDo;
    }
}

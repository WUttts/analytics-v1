package com.analytics.v1.infrastructure.gateway;

import com.analytics.v1.domain.dim.DimTable;
import com.analytics.v1.domain.point.PointTable;
import com.analytics.v1.infrastructure.common.constants.TableConstant;
import com.analytics.v1.infrastructure.common.exception.BadRequestException;
import com.analytics.v1.infrastructure.convert.TableConvert;
import com.analytics.v1.infrastructure.gateway.db.DimTableDo;
import com.analytics.v1.infrastructure.gateway.db.PointTableDo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author wutangsheng
 * @create 2022-05-10 1:52 下午
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class TableGateway {
    private static final String SELECT_WHERE_IN_SQL = "select * from %s where id in (%s)";
    private final JdbcTemplate jdbcTemplate;

    public List<DimTable> dimTables(Set<String> tableNames) {
        String sql = format(TableConstant.DIM_TABLE, tableNames);
        List<DimTableDo> tableDos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DimTableDo.class));

        if (tableDos.isEmpty()) {
            throw new BadRequestException("所选的所有维度没有找到对应的表：" + tableNames);
        }
        return TableConvert.toDimTable(tableDos);
    }


    public List<PointTable> pointsTables(Set<String> tableNames) {
        String sql = format(TableConstant.DIM_TABLE, tableNames);
        List<PointTableDo> tableDos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PointTableDo.class));

        return TableConvert.toPointTable(tableDos);
    }


    private String format(String db, Set<String> tableNames) {
        StringBuilder join = new StringBuilder();
        for (String name : tableNames) {
            join.append(name).append(",");
        }
        String substring = join.substring(0, join.length() - 1);
        return String.format(TableGateway.SELECT_WHERE_IN_SQL, db, substring);
    }
}

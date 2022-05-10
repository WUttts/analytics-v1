package com.analytics.v1.infrastructure.convert;

import com.analytics.v1.domain.dim.DimTable;
import com.analytics.v1.domain.point.PointTable;
import com.analytics.v1.infrastructure.gateway.db.DimTableDo;
import com.analytics.v1.infrastructure.gateway.db.PointTableDo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-05-10 1:53 下午
 * @desc
 */
public class TableConvert {

    public static DimTable toDimTable(DimTableDo dimTableDo) {
        DimTable dimTable = new DimTable();
        dimTable.setId(dimTableDo.getId());
        dimTable.setDimTableName(dimTableDo.getDimTableName());
        dimTable.setDimCountParttio(dimTableDo.getDimCountParttio());
        return dimTable;
    }

    public static List<DimTable> toDimTable(List<DimTableDo> dimTableDos) {
        List<DimTable> list = new ArrayList<>(dimTableDos.size());
        for (DimTableDo dimTableDo : dimTableDos) {
            list.add(toDimTable(dimTableDo));
        }
        return list;
    }

    public static PointTable toPointTable(PointTableDo pointTableDo) {
        PointTable pointTable = new PointTable();
        pointTable.setId(pointTableDo.getId());
        pointTable.setPointTableName(pointTableDo.getPointTableName());
        pointTable.setPointCountParttio(pointTableDo.getPointCountParttio());
        return pointTable;
    }

    public static List<PointTable> toPointTable(List<PointTableDo> pointTableDos) {
        List<PointTable> list = new ArrayList<>(pointTableDos.size());
        for (PointTableDo pointTableDo : pointTableDos) {
            list.add(toPointTable(pointTableDo));
        }
        return list;
    }
}

package com.analytics.v1.infrastructure.convert;

import com.analytics.v1.domain.dim.DimInfo;
import com.analytics.v1.infrastructure.common.enums.DimRuleEnum;
import com.analytics.v1.infrastructure.gateway.db.DimDo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:27 下午
 * @desc
 */
public class DimConvert {

    public static DimInfo toDomain(DimDo dimDo) {
        DimInfo dimInfo = new DimInfo();
        dimInfo.setId(dimDo.getId());
        dimInfo.setDimFieldName(dimDo.getDimFieldName());
        dimInfo.setDimDescribe(dimDo.getDimDescribe());
        dimInfo.setDimRule(DimRuleEnum.KEYWORDS);
        dimInfo.setDimName(dimDo.getDimName());
        return dimInfo;
    }

    public static List<DimInfo> toDomain(List<DimDo> dimDos) {
        List<DimInfo> list = new ArrayList<>(dimDos.size());
        for (DimDo dimDo : dimDos) {
            list.add(toDomain(dimDo));
        }
        return list;
    }


}

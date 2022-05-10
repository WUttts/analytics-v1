package com.analytics.v1.infrastructure.convert;

import com.analytics.v1.domain.point.PointInfo;
import com.analytics.v1.infrastructure.common.enums.PointRuleEnum;
import com.analytics.v1.infrastructure.gateway.db.PointDo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:27 下午
 * @desc
 */
public class PointConvert {

    public static PointInfo toDomain(PointDo pointDo) {
        PointInfo pointInfo = new PointInfo();
        pointInfo.setId(pointDo.getId());
        pointInfo.setPointFieldName(pointDo.getPointFieldName());
        pointInfo.setPointDescribe(pointDo.getPointDescribe());

        PointRuleEnum pointRuleEnum = PointRuleEnum.convert(pointDo.getPointRule());
        pointInfo.setPointRule(pointRuleEnum);

        pointInfo.setPointName(pointDo.getPointName());

        pointInfo.check();

        return pointInfo;
    }

    public static List<PointInfo> toDomain(List<PointDo> pointDos) {
        List<PointInfo> list = new ArrayList<>(pointDos.size());
        for (PointDo pointDo : pointDos) {
            list.add(toDomain(pointDo));
        }
        return list;
    }
}

package com.analytics.v1.app.cmd;

import com.analytics.v1.domain.point.PointInfo;
import com.analytics.v1.domain.point.PointMeta;
import com.analytics.v1.infrastructure.gateway.PointGateway;
import com.analytics.v1.infrastructure.gateway.TableGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wutangsheng
 * @create 2022-05-09 4:20 下午
 * @desc
 */
@Component
@RequiredArgsConstructor
public class PointExe {
    private final PointGateway pointGateway;
    private final TableGateway tableGateway;

    public PointMeta execute(List<Integer> ids) {
        List<PointInfo> points = this.pointGateway.findPoints(ids);

        return new PointMeta();
    }
}

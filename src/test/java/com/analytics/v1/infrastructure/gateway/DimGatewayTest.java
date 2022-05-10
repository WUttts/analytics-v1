package com.analytics.v1.infrastructure.gateway;

import com.analytics.v1.domain.dim.DimInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wutangsheng
 * @create 2022-05-09 6:13 下午
 * @desc
 */
@SpringBootTest
class DimGatewayTest {
    @Autowired
    private DimGateway dimGateway;


    @Test
    public void testDims() {
        List<Integer> list = Arrays.asList(3, 4);
        List<DimInfo> dims = dimGateway.findDim(list);
        System.out.println(dims);
    }
}
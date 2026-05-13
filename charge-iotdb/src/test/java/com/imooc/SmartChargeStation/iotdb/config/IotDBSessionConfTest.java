package com.imooc.SmartChargeStation.iotdb.config;

import org.apache.iotdb.session.pool.SessionPool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {IotDBSessionConfTest.class})
class IotDBSessionConfTest {

    /**
     * author: Imooc
     * description: 测试iotdb Session初始化
     * @param :
     * @return void
     */
    @Test
    void testSessionConf() {

        IotDBSessionConf conf = new IotDBSessionConf();
        SessionPool pool = conf.initSessionPool();
        System.out.println(pool);
        System.out.println(pool.getVersion());
    }

}
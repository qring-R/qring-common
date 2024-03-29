package com.qring.common.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Qring
 * @Description 启动类
 * @Date 2022/8/18 14:18
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.qring.common"})
// @NacosPropertySource(dataId = "test", autoRefreshed = true)
@MapperScan(basePackages = {"com.qring.common.test.repository"})
public class QringCommonTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(QringCommonTestApplication.class, args);
    }

}

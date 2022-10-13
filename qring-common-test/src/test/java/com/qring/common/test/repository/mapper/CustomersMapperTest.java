package com.qring.common.test.repository.mapper;

import com.qring.common.test.Application;
import com.qring.common.test.quartz.QuartzTaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author Qring
 * @Description TODO
 * @Date 2022/8/25 15:10
 * @Version 1.0
 */
@Slf4j
@SpringBootTest(classes = Application.class)
class CustomersMapperTest {
    @Resource
    private QuartzTaskUtil quartzTaskUtil;

    @Test
    public void test() {

    }
}
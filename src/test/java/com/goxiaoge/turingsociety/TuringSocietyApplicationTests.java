package com.goxiaoge.turingsociety;

import com.goxiaoge.turingsociety.mapper.ApplicationMapper;
import com.goxiaoge.turingsociety.pojo.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TuringSocietyApplicationTests {

    @Resource
    ApplicationMapper mapper;

    @Test
    void contextLoads() {
        mapper.selectList(null).forEach(System.out::println);
        // System.out.println(mapper.selectById(1539563355993448449L));
    }

}

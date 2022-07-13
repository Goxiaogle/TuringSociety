package com.goxiaoge.turingsociety;

import com.goxiaoge.turingsociety.enums.ApplicationStatus;
import com.goxiaoge.turingsociety.pojo.Application;
import com.goxiaoge.turingsociety.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ApplicationTest {

    @Resource
    ApplicationService service;

    @Test
    public void testUpdate() {
        Application application = Application.builder()
                .id(1539563355993448449L)
                .status(ApplicationStatus.REFUSE).build();
        service.updateById(application);
    }

    @Test
    public void testInsert() {
        Application application = new Application();
        System.out.println(service.save(application));
        System.out.println(application.getId());
    }

}

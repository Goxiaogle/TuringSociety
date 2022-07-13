package com.goxiaoge.turingsociety;

import com.goxiaoge.turingsociety.pojo.Application;
import com.goxiaoge.turingsociety.utils.chainbuilder.StringChainBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuickBuildTest {

    @Test
    public void testQuickBuild() {
        Application application = Application.builder()
                .name("张三")
                .build();
        Integer end = new StringChainBuilder<>(1, true)
                .quickBuilder(application)
                .end(2);
        System.out.println(end);
    }

}

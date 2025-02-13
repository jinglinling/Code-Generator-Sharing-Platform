package com.jinglinling.web.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @Auther 谢骏晖
 * @Date 2025/2/10
 * @Description
 */
@SpringBootTest
class CosManagerTest {

    @Resource
    private CosManager cosManager;

    @Test
    void deleteObject() {
        cosManager.deleteObject("/test/test1.jpg");
    }

    @Test
    void deleteObjects() {
        cosManager.deleteObjects(Arrays.asList("test/test2.jpg", "test/test3.jpg"));
    }

    @Test
    void deleteDir() {
        cosManager.deleteDir("/test/");
    }
}
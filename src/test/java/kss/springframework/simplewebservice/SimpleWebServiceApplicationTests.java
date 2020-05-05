package kss.springframework.simplewebservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleWebServiceApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {

        Thread.sleep(2000);
        System.out.println("...");
        Thread.sleep(2000);
        System.out.println(".......");
        Thread.sleep(2000);
        System.out.println("..........");
        Thread.sleep(2000);
        System.out.println("...............");
        Thread.sleep(2000);
        System.out.println("...................");
    }

}

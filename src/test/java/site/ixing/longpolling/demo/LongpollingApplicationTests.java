package site.ixing.longpolling.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import site.ixing.longpolling.demo.service.AsyncFeignService;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest(classes = LongpollingApplication.class)
class LongpollingApplicationTests {

    private RestTemplate restTemplate=new RestTemplate();

    @Test
    void contextLoads() throws Exception{
        ExecutorService executorService= Executors.newFixedThreadPool(200);
        for(int i=0;i<=100;i++){
            executorService.execute(()->{
                ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/async/longPolling", String.class);
                System.out.println(forEntity.getBody());
            });
        }
        System.in.read();
    }

    @Test
    void returnLongPolling(){
        restTemplate.getForEntity("http://localhost:8080/async/returnLongPollingValue", Void.class);
    }

}

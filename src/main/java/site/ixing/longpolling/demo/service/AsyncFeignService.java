package site.ixing.longpolling.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liuzhifei
 * @version 1.0
 * @date 2020/11/13 1:53 下午
 */
@FeignClient(url = "localhost:8080",name = "async")
@Service
public interface AsyncFeignService {
    @GetMapping("/async/longPollling")
    String longPolling();

    @GetMapping("/async/returnLongPollingValue")
    void returnLongPollingValue();
}

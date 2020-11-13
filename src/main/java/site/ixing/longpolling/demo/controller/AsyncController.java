package site.ixing.longpolling.demo.controller;

import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuzhifei
 * @version 1.0
 * @date 2020/11/13 11:44 上午
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    final Map<Integer,DeferredResult> deferredResultMap = new ConcurrentReferenceHashMap();
    @GetMapping("/longPolling")
    public DeferredResult DeferredResultlongPolling(){
        DeferredResult deferredResult=new DeferredResult(60000L);
        deferredResultMap.put(deferredResult.hashCode(),deferredResult);
        deferredResult.onCompletion(()->{
            deferredResultMap.remove(deferredResult.hashCode());
            System.err.println("还剩"+deferredResultMap.size()+"个deferredResult未响应");
        });
        deferredResult.onTimeout(()->{
            deferredResult.setResult("304");
        });
        return deferredResult;
    }

    @GetMapping("/returnLongPollingValue")
    public void returnLongPollingValue(){
        for (Map.Entry<Integer, DeferredResult> resultEntry : deferredResultMap.entrySet()) {
            resultEntry.getValue().setResult("qaq");
        }
    }
}

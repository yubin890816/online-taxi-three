package com.yubin.apipassenger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class ApiPassengerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication.class, args);
    }

    LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            // set的值5s后过期
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                // get的时候没有获取到值则取该方法的结果
                @Override
                public String load(String key) throws Exception {
                    return "load:" + new Random().nextInt(100);
                }
            });

    @PostMapping("/test-set/{id}")
    public String testSet(@PathVariable("id") String id) {
        // 往缓存中设值
        cache.put(id, "set:" + new Random().nextInt(100));
        return "success";
    }

    @GetMapping("/test-get/{id}")
    public String testFet(@PathVariable("id") String id) throws ExecutionException {
        // 从缓存中取值
        String value = cache.get(id).toString();
        return value;
    }
}

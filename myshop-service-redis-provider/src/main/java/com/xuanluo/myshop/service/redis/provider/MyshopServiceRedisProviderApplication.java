package com.xuanluo.myshop.service.redis.provider;

import com.alibaba.dubbo.container.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication(scanBasePackages = "com.xuanluo.myshop")
public class MyshopServiceRedisProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyshopServiceRedisProviderApplication.class, args);
        Main.main(args);
    }
}

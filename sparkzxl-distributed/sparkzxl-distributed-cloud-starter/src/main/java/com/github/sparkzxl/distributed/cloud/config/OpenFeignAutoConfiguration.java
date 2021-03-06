package com.github.sparkzxl.distributed.cloud.config;

import com.github.sparkzxl.distributed.cloud.hystrix.ThreadLocalHystrixConcurrencyStrategy;
import com.github.sparkzxl.distributed.cloud.interceptor.FeignAddHeaderRequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: OpenFeign 配置
 *
 * @author: zhouxinlei
 * @date: 2020-07-12 16:32:22
 */
@Configuration
@ConditionalOnClass(FeignAutoConfiguration.class)
public class OpenFeignAutoConfiguration {

    public OpenFeignAutoConfiguration() {

    }

    /**
     * feign client 请求头传播
     *
     * @return FeignAddHeaderRequestInterceptor
     */
    @Bean
    public FeignAddHeaderRequestInterceptor getClientTokenInterceptor() {
        return new FeignAddHeaderRequestInterceptor();
    }

    /**
     * 本地线程 Hystrix并发策略
     *
     * @return ThreadLocalHystrixConcurrencyStrategy
     */
    @Bean
    public ThreadLocalHystrixConcurrencyStrategy getThreadLocalHystrixConcurrencyStrategy() {
        return new ThreadLocalHystrixConcurrencyStrategy();
    }
}

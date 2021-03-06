package com.github.sparkzxl.database.config;

import com.github.sparkzxl.database.aspect.InjectionResultAspect;
import com.github.sparkzxl.database.injection.InjectionCore;
import com.github.sparkzxl.database.mybatis.hander.RemoteDataTypeHandler;
import com.github.sparkzxl.database.properties.InjectionProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:关联字段数据注入工具 自动配置类
 *
 * @author: zhouxinlei
 * @date: 2020-07-19 09:15:48
 */
@Configuration
@EnableConfigurationProperties(InjectionProperties.class)
public class InjectionDataAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public InjectionCore injectionCore(InjectionProperties injectionProperties) {
        return new InjectionCore(injectionProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = {"sparkzxl.injection.aop-enabled"}, havingValue = "true", matchIfMissing = true)
    public InjectionResultAspect getRemoteAspect(InjectionCore injectionCore) {
        return new InjectionResultAspect(injectionCore);
    }

    /**
     * Mybatis 类型处理器： 处理 RemoteData 类型的字段
     *
     * @return RemoteDataTypeHandler
     */
    @Bean
    @ConditionalOnMissingBean
    public RemoteDataTypeHandler remoteDataTypeHandler() {
        return new RemoteDataTypeHandler();
    }

}

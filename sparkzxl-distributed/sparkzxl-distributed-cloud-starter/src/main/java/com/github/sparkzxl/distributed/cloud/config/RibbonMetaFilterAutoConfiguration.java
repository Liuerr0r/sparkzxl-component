package com.github.sparkzxl.distributed.cloud.config;

import com.github.sparkzxl.distributed.cloud.ribbon.GrayRule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * description: 配置ribbon 相关
 *
 * @author: zhouxinlei
 * @date: 2020-07-12 16:16:47
 */
@Configuration
@AutoConfigureBefore(RibbonClientConfiguration.class)
public class RibbonMetaFilterAutoConfiguration {

    /**
     * 灰度发布 规则
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public GrayRule metadataAwareRule() {
        return new GrayRule();
    }
}

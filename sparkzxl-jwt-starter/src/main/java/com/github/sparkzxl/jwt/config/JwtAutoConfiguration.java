package com.github.sparkzxl.jwt.config;

import com.github.sparkzxl.jwt.properties.JwtProperties;
import com.github.sparkzxl.jwt.properties.KeyStoreProperties;
import com.github.sparkzxl.jwt.service.impl.JwtTokenServiceImpl;
import com.google.common.collect.Maps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: jwt自动装配
 *
 * @author: zhouxinlei
 * @date: 2020-07-14 08:08:02
 */
@Configuration
@EnableConfigurationProperties({JwtProperties.class, KeyStoreProperties.class})
public class JwtAutoConfiguration {

    @Bean
    public JwtTokenServiceImpl jwtTokenService(JwtProperties jwtProperties, KeyStoreProperties KeyStoreProperties) {
        return new JwtTokenServiceImpl(jwtProperties,KeyStoreProperties, Maps.newConcurrentMap());
    }
}

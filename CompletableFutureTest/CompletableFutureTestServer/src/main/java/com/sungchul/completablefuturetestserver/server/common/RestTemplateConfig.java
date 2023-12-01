package com.sungchul.completablefuturetestserver.server.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 연결 타임아웃 설정 (5초)
        factory.setConnectTimeout(10000);
        // 읽기 타임아웃 설정 (5초)
        factory.setReadTimeout(10000);
        return factory;
    }
}
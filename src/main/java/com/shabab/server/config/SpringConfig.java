package com.shabab.server.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
public class SpringConfig {

    @Bean
    public OkHttpClient okHttpClient(@Value("${okhttp.keep.alive.millis:120000}") int keepAliveInMillis,
                                     @Value("${okhttp.max.connections:3}") int maxConnections,
                                     @Value("${okhttp.max.connections.timeout.millis:1000}") int maxConnectionTimeoutInMillis) {

        ConnectionPool cp = new ConnectionPool(maxConnections, keepAliveInMillis, TimeUnit.MILLISECONDS);

        return new OkHttpClient.Builder()
                .readTimeout(maxConnectionTimeoutInMillis, TimeUnit.MILLISECONDS)
                .callTimeout(maxConnectionTimeoutInMillis, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .connectTimeout(maxConnectionTimeoutInMillis, TimeUnit.MILLISECONDS)
                .connectionPool(cp)
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


}

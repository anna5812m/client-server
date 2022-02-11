package com.example.clientserver.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "client-server")
@Data
public class RepetitionRestClientProperties {
    private String url;
    private String basePath;
}

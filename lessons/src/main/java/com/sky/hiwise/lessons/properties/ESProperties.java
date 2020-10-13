package com.sky.hiwise.lessons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "elasticsearch")
public class ESProperties {

    private String clusterName;

    private String ip;

    private String port;

    private int pool;

}

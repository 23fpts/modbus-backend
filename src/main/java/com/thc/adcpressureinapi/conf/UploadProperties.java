package com.thc.adcpressureinapi.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author thc
 * @Title:
 * @Package com.coffee.xcx.config
 * @Description:
 * @date 2021/1/20 7:44 下午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload")
public class UploadProperties {
    private String filePath;
    private String readPrefix;
}

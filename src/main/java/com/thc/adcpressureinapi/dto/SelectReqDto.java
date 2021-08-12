package com.thc.adcpressureinapi.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author thc
 * @Title:
 * @Package com.thc.adcpressureinapi
 * @Description:
 * @date 2021/7/19 7:19 下午
 */
@Data
public class SelectReqDto {
    private String startTime;
    private String endTime;
    private Integer channel;
}

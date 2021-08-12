package com.thc.adcpressureinapi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author thc
 * @Title:
 * @Package com.thc.adcpressureinapi.model
 * @Description:
 * @date 2021/7/7 7:34 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("period")
public class Period {

    private String id;

    private Date startTime;

    private Date endTime;
}

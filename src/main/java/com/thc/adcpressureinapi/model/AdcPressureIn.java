package com.thc.adcpressureinapi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author thc
 * @Title:
 * @Package com.thc.adcpressureinapi.model
 * @Description:
 * @date 2021/7/6 5:32 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("adc_pressure_in")
public class AdcPressureIn implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    private Integer year;
    private Integer mon;
    private Integer day;
    private Integer hour;
    private Integer min;
    private Integer sec;

    private Date gmtTime;

    private Integer num;
    private Integer state;
    private Integer channel;
    private Integer timeInterval;

    private String periodId;
}

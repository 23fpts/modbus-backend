package com.thc.adcpressureinapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.thc.adcpressureinapi.dto.SelectReqDto;
import com.thc.adcpressureinapi.mapper.AdcPressureInMapper;
import com.thc.adcpressureinapi.mapper.PeriodMapper;
import com.thc.adcpressureinapi.model.AdcPressureIn;
import com.thc.adcpressureinapi.model.Period;
import com.thc.adcpressureinapi.response.BaseResult;
import com.thc.adcpressureinapi.utils.CommonUtil;
import com.thc.adcpressureinapi.utils.DatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.adcpressureinapi.controller
 * @Description:
 * @date 2021/7/6 5:49 下午
 */
@RestController
@RequestMapping("/adc-pressure-in")
@Slf4j
public class AdcPressureInController {

    @Autowired
    private AdcPressureInMapper adcPressureInMapper;

    @Autowired
    private PeriodMapper periodMapper;

    @GetMapping("/uploadDat2Database")
    public BaseResult uploadDat2Database(@RequestParam("filePath") String filePath) throws IOException {
        // 先转换数据
        List<AdcPressureIn> adcPressureIns = DatUtil.datFile2Database(filePath);
        // TODO 存储之前还要存储period表
        // 获取第一个和最后一个数据的时间：
        Date startTime = param2DateByObject(adcPressureIns.get(0));
        Date endTime = param2DateByObject(adcPressureIns.get(adcPressureIns.size()-1));

        // 存储
        Period period = new Period();
        period.setStartTime(startTime);
        period.setEndTime(endTime);
        periodMapper.insert(period);

        // 在存储数据
        for (AdcPressureIn item: adcPressureIns) {
            item.setPeriodId(period.getId());
            item.setGmtTime(param2DateByObject(item));
            adcPressureInMapper.insert(item);
        }
        return BaseResult.success();
    }


    // 查询，时间段，通道（channel）
    @PostMapping("/select")
    public BaseResult select(@RequestBody SelectReqDto dto
                             ) {
        LambdaQueryWrapper<AdcPressureIn> wrapper = new LambdaQueryWrapper<>();

        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        Integer channel = dto.getChannel();
        log.info("startTime: {}, endTime: {}, channel: {}", startTime, endTime, channel);

        if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
            wrapper.between(AdcPressureIn::getGmtTime, startTime, endTime);
        }
        if (channel!=null) {
            wrapper.eq(AdcPressureIn::getChannel, channel);
        }
        List<AdcPressureIn> list = adcPressureInMapper.selectList(wrapper);
        return BaseResult.success(list);
    }

    // 删除， 根据时间段来删除，按照通道删除
    @PostMapping("/delete")
    public BaseResult delete (@RequestBody SelectReqDto dto) {
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        Integer channel = dto.getChannel();
        LambdaQueryWrapper<AdcPressureIn> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
            wrapper.between(AdcPressureIn::getGmtTime, startTime, endTime);
        }
        if (channel!=null) {
            wrapper.eq(AdcPressureIn::getChannel, channel);
        }
        adcPressureInMapper.delete(wrapper);
        return BaseResult.success();
    }

    private Date param2DateByObject(AdcPressureIn adcPressureIn) {
        try {
            return CommonUtil.param2Date(adcPressureIn.getYear(), adcPressureIn.getMon(), adcPressureIn.getDay(), adcPressureIn.getHour(), adcPressureIn.getMin(), adcPressureIn.getSec());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.thc.adcpressureinapi.utils;

import com.thc.adcpressureinapi.model.AdcPressureIn;
import com.thc.adcpressureinapi.model.ModBusObject;

import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.adcpressureinapi.utils
 * @Description:
 *
 * 通信用的232（点对点，100m）还是485（点对多点，1.2km），
 * 串行速率，串行速率格式，一串数据，11位，10位数据传输，起始位（1），数据位（8） ，校验位（1），停止位（1），一个字节拆成，
 * 速率匹配（4800-115200bps），数据格式匹配后，232会自动解析数据位
 * 调用232的api接口，
 *
 *
 * q区地址 X和Y和图片上的8位不一致
 *
 * @date 2021/8/10 8:05 下午
 */
public class ModBusUtil {

    public static List<ModBusObject> dataDeal(byte[] bytes) {
        return null;
    }
}

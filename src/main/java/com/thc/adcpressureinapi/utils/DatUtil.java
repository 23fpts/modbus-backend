package com.thc.adcpressureinapi.utils;

import com.thc.adcpressureinapi.model.AdcPressureIn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.adcpressureinapi.utils
 * @Description: <h1>处理dat文件的工具类</h1>
 * @date 2021/7/6 5:26 下午
 */
public class DatUtil {


    public static List<AdcPressureIn> datFile2Database(String fileName) throws IOException {
        byte [] bytes = getDatFile(fileName);
        return dataDeal(bytes);
    }

    private static byte[] getDatFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        byte[] fileContents =  Files.readAllBytes(path);
//        int j=0;
//        for (int i = 0; i < fileContents.length; i++) {
//            System.out.print(fileContents[i]+" ");
//            j++;
//            if (j==11){
//                System.out.println();
//                j=0;
//            }
//        }
        return fileContents;
    }

    private static List<AdcPressureIn> dataDeal(byte[] bytes) {
        List<AdcPressureIn> adcPressureInList = new ArrayList<>();
        int j=0;
        AdcPressureIn adcPressureIn = new AdcPressureIn();
        for (int i = 0; i < bytes.length; i++) {
            j++;
            if (j==12)
                j=1;
            if (j==1) {
                adcPressureIn = new AdcPressureIn();
                adcPressureIn.setYear((int)bytes[i]);
            } else if (j==2) {
                adcPressureIn.setMon((int)bytes[i]);
            } else if (j==3) {
                adcPressureIn.setDay((int)bytes[i]);
            } else if (j==4) {
                adcPressureIn.setHour((int)bytes[i]);
            } else if (j==5) {
                adcPressureIn.setMin((int)bytes[i]);
            } else if (j==6) {
                adcPressureIn.setSec((int)bytes[i]);
            } else if (j==7) {
                adcPressureIn.setNum((bytes[i]&0x0FF) *256+ (bytes[i+1]&0x0FF));
            } else if (j==8) {
                continue;
            }
            else if (j==9) {
                adcPressureIn.setState((int)bytes[i]);
            } else if (j==10) {
                adcPressureIn.setChannel((int)bytes[i]);
            } else if (j==11) {
                adcPressureIn.setTimeInterval((int)bytes[i]);
                adcPressureInList.add(adcPressureIn);
            }
        }
        return adcPressureInList;
    }


}

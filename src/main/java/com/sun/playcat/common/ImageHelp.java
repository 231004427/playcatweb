package com.sun.playcat.common;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by sunlin on 2017/7/6.
 */
public class ImageHelp {
    /**
     * 通过BASE64Decoder解码，并生成图片
     * @param imgStr 解码后的string
     */
    public static byte[] string2Image(String imgStr) {
        // 对字节数组字符串进行Base64解码并生成图片
            // Base64解码
        byte[] b = new byte[0];
        try {
            b = new BASE64Decoder().decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    // 调整异常数据
                    b[i] += 256;
                }
            }
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}

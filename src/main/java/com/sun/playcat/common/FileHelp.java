package com.sun.playcat.common;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by sunlin on 2017/7/6.
 */
public class FileHelp {
    public static void Save(String saveFilePath,String fileName,byte[] data){
        /* 构建文件目录 */
        File fileDir = new File(saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {
            FileOutputStream out = new FileOutputStream(saveFilePath+"/"+fileName);
            // 写入文件
            out.write(data);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String SaveHeadBase64(HttpServletRequest request,String baseCode,int userid)
    {
        byte[] data=ImageHelp.string2Image(baseCode);
        String path = request.getServletContext().getRealPath("");
        String filePath = null;

        int num1=(int)Math.floor(userid/1000);
        int num2=(int)Math.floor(num1/1000);

        filePath = "/headImg/"+num2+"/"+num1;
        String fileName =userid+".jpg";
        Save(path + filePath,fileName,data);
        return filePath+"/"+fileName;

    }
    public static String SaveHeadBase64(HttpServletRequest request,String baseCode) {

        try {
            Calendar now = Calendar.getInstance();

            byte[] data = ImageHelp.string2Image(baseCode);
            String path = request.getServletContext().getRealPath("");
            String filePath = null;

            filePath = "/statics/headImg/" + now.get(Calendar.YEAR) + "/" + now.get(Calendar.MONTH) + "/" + now.get(Calendar.DAY_OF_MONTH);

            SimpleDateFormat simpleDateFormat;
            simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String str = simpleDateFormat.format(new Date());
            Random random = new Random();
            int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

            String fileName = rannum + str+".jpg";
            Save(path + filePath, fileName, data);
            return filePath + "/" + fileName;
        }catch (Exception e)
        {
            Log.error(e.toString());
            return "";
        }
    }
}

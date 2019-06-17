package Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {
    public static boolean isNull(String s){
        return s == null || s.equals("");
    }

    public static String getRandomFileName() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmssZ");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return rannum + str;// 当前时间
    }

}

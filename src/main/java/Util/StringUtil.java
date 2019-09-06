package Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {
    public static boolean isNull(String s){
        return s == null || s.equals("");
    }

    public static String getRandomFileName() {

        /**
         * 可以使用DateTimeFormatter代替SimpleDateFormat
         * 因为SimpleDateFormat是非线程安全的
         * 不过由于我这里每次都会new一个新的SimpleDateFormat实例,所以不会发生线程安全问题
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmssZ");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return rannum + str;// 当前时间
    }

}

package HttpHeadParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 解析HTTP报文头是爬虫常常做的事情
 */
public class DemoHttpHeadParser {

    public static Map<String, List<String>>  getHeader(URL url) throws IOException {

        if(url == null){
            return null;
        }

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        return urlConnection.getHeaderFields();
    }

    //通过连接API来获取各种各样的数据
}

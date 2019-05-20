package ldg.study.springboot.tools.utils.http;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Map;

/**
 * @author： ldg
 * @create date： 2018/12/18
 */
public class HttpUtils {
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlName = url;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setConnectTimeout(4000);
            // 建立实际的连接
            conn.connect();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送GET请求出现异常！" + e);
        } finally {// 使用finally块来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("关闭流异常");
            }
        }
        return result;
    }

    public static Map getRequestBodyToMap(HttpServletRequest request) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String str = "";
            StringBuilder sbBody = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                sbBody.append(str);
            }
            return JSONObject.parseObject(sbBody.toString());
        } catch (Exception io) {
            return Collections.EMPTY_MAP;
        }
    }
}

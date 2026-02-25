package com.springcloud.ms.controller.util;

import com.springcloud.ms.controller.decrypt.AesUtil;
import com.springcloud.ms.controller.decrypt.DesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: yaorp
 */
@Slf4j
public class SearchHttpAK {
    public static String URL = "https://api.map.baidu.com/geocoding/v3?";

    public static String AK = "xVj2tS70bUHVabG0wOpMIbExZWqpMxfg";

    public static void main(String[] args) throws Exception {

        SearchHttpAK snCal = new SearchHttpAK();

        Map params = new LinkedHashMap<String, String>();
        params.put("address", "北京市海淀区上地十街10号");
        params.put("output", "json");
        params.put("ak", AK);
//        params.put("callback", "showLocation");


        snCal.requestGetAK(URL, params);
    }

    /**
     * 默认ak
     * 选择了ak，使用IP白名单校验：
     * 根据您选择的AK已为您生成调用代码
     * 检测到您当前的ak设置了IP白名单校验
     * 您的IP白名单中的IP非公网IP，请设置为公网IP，否则将请求失败
     * 请在IP地址为xxxxxxx的计算发起请求，否则将请求失败
     */
    public void requestGetAK(String strUrl, Map<String, String> param) throws Exception {
        if (strUrl == null || strUrl.length() <= 0 || param == null || param.size() <= 0) {
            return;
        }

        StringBuffer queryString = new StringBuffer();
        queryString.append(strUrl);
        for (Map.Entry<?, ?> pair : param.entrySet()) {
            queryString.append(pair.getKey() + "=");
            //    第一种方式使用的 jdk 自带的转码方式  第二种方式使用的 spring 的转码方法 两种均可
            //    queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8").replace("+", "%20") + "&");
            queryString.append(UriUtils.encode((String) pair.getValue(), "UTF-8") + "&");
        }

        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }

        URL url = new URL(queryString.toString());
        System.out.println(queryString.toString());
        URLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();

        InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        isr.close();
        System.out.println("AK: " + buffer.toString());
    }


    public void push(String IPAddress, String data, boolean MT, boolean alibaba) {
        try {
            URL url = new URL(IPAddress);
            // 打开和URL之间的连接
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            // 调用URL的openConnection()方法,获取HttpURLConnection对象
            con.setRequestMethod("POST");// 请求post方式
            con.setUseCaches(false); // Post请求不能使用缓存
            con.setDoInput(true);// 设置是否从HttpURLConnection输入，默认值为 true
            con.setDoOutput(true);// 设置是否使用HttpURLConnection进行输出，默认值为 false
            // 设置header内的参数 connection.setRequestProperty("健, "值");
            con.setRequestProperty("Content-Type", "text/plain");
            con.setRequestProperty("isTree", "true");
            con.setRequestProperty("isLastPage", "true");
            con.setReadTimeout(10000);
            // 设置body内的参数，put到JSONObject中
            log.info("url：{}",IPAddress);
            log.info("发送数据：{}",data);
            // 建立实际的连接
            con.connect();
            // 得到请求的输出流对象
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8);
            writer.write(data);
            writer.flush();
            writer.close();
            // 获取服务端响应，通过输入流来读取URL的响
            InputStream is = con.getInputStream();
            String response = getStringFromInputStream(is);
            // 打印读到的响应结果
            log.info("url" + IPAddress + "---密文响应结果：" + response);
            // 关闭连接
            con.disconnect();
            if (MT) {
                response = AesUtil.aesDecrypt(response);
            } else if (!alibaba) {
                response = DesUtil.getInstance().decrypt(response);
            }
            log.info("url" + IPAddress + "---明文响应结果：" + response);
            // 将响应结果转换为json字符串
//            JSONObject jsonObj = JSONObject.fromObject(response);
//            JsonObject obj = new JsonParser().parse(jsonObj.toString()).getAsJsonObject();
//            return new PushResult(true, obj);
        } catch (Exception e) {
            log.info("发送失败，url:{}", IPAddress,e);
        }
    }

    /**
     * 根据流返回一个字符串信息*
     */
    private static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        // 一定要写len=is.read(buffer)
        // 如果while((is.read(buffer))!=-1)则无法将数据写入buffer中
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        os.close();
        return state;
    }

}

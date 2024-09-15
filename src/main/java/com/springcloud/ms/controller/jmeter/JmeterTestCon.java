package com.springcloud.ms.controller.jmeter;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Jmeter测试
 * @author: yaorp
 */

@RestController
@RequestMapping("/jmeter/test")
public class JmeterTestCon {

    static class StaticObject{
    }

    static class User {
        private Long id;
        private String name;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    @PostMapping("/qrySuc")
    public RspData qrySuc() throws InterruptedException {
//        Thread.sleep(10*1000);
        System.out.println("qrySuc。。。。。。");
        return RspData.suc();
    }

    @GetMapping("/qryOne")
    public RspData qryOne(){
//        return "hello word";

        ArrayList<StaticObject> list = new ArrayList<>();
        int i=1;
        while (true){
            list.add(new StaticObject());
            i++;
            System.out.println("i:"+i+",list.size:"+list.size());
        }
//        return RspData.suc();
    }

    @GetMapping("/qryOne2")
    public RspData qryOne2(){
        long start = System.currentTimeMillis();
        System.out.println("start:"+start);
        try {
            RequestBody req = new RequestBody();
            String imgPath = "/Users/yaorp/document/69791725587590_.pic_hd.jpg";
            req.setImageStr(ImageUtil.convertImageToBase64Str(imgPath));
            req.setImageStr2(ImageUtil.convertImageToBase64Str(imgPath));
            req.setImageStr3(ImageUtil.convertImageToBase64Str(imgPath));
            req.setImageStr4(ImageUtil.convertImageToBase64Str(imgPath));
//            String response = HttpUtil.createPost("http://192.168.126.10:903/test")
            String response = HttpUtil.createPost("http://127.0.0.1:9011/jmeter/test/qrySuc")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body("{}")
                    .execute().body();
//            System.out.println("response:"+response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(System.currentTimeMillis() - start);
        }

        return RspData.suc();
    }

    @GetMapping("/qryOne3")
    public RspData qryOne3(){
        long start = System.currentTimeMillis();
        System.out.println("start:"+start);
        try {
            RequestBody req = new RequestBody();
            String imgPath = "/Users/yaorp/document/69791725587590_.pic_hd.jpg";
            String imgPath2 = "/Users/yaorp/document/69791725587590_.pic_hd.jpg";
            String imgPath3 = "/Users/yaorp/document/69791725587590_.pic_hd.jpg";
            String imgPath4 = "/Users/yaorp/document/69791725587590_.pic_hd.jpg";
            String imgPath5 = "/Users/yaorp/document/69791725587590_.pic_hd.jpg";
            String imgPath6 = "/Users/yaorp/document/69791725587590_.pic_hd.jpg";
            req.setImageStr(ImageUtil.convertImageToBase64Str(imgPath));
            req.setImageStr2(ImageUtil.convertImageToBase64Str(imgPath2));
            req.setImageStr3(ImageUtil.convertImageToBase64Str(imgPath3));
            req.setImageStr4(ImageUtil.convertImageToBase64Str(imgPath4));
            req.setImageStr5(ImageUtil.convertImageToBase64Str(imgPath5));
            req.setImageStr6(ImageUtil.convertImageToBase64Str(imgPath6));
            String url = "http://127.0.0.1:9012/jmeter/test/qrySuc";
            String httpRspString = HttpClientUtil.doPostJson(url, JSONObject.toJSONString(req));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(System.currentTimeMillis() - start);
        }

        return RspData.suc();
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        while (true){
            User user = new User();
            user.setName(UUID.randomUUID().toString());
            list.add(user);
        }
    }

}

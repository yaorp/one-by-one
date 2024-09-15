package com.springcloud.ms.controller.jmeter;

import lombok.Data;

/**
 * @author: yaorp
 */
@Data
public class RspData {
    private String code;
    private String msg;

    public RspData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RspData suc(){
        return new RspData("0000","交易成功");
    }

    public static RspData suc(String code, String msg){
        return new RspData(code,msg);
    }

    public static RspData suc(String msg){
        return new RspData("0000",msg);
    }
}

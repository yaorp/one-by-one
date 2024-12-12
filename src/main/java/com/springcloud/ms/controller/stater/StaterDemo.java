package com.springcloud.ms.controller.stater;

import com.springcloud.ms.WrapTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: yaorp
 */
@RestController
public class StaterDemo {

//    @Resource
    private WrapTemplate wrapTemplate;

    @GetMapping("/wrap")
    public String wrap(@RequestParam String param){
        return wrapTemplate.wrap(param);
    }

}

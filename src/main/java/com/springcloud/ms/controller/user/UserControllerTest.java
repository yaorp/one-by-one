package com.springcloud.ms.controller.user;

import com.springcloud.ms.annotation.LogExecutionTime;
import com.springcloud.ms.controller.pattern.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserControllerTest {

    @LogExecutionTime(value = "queryUser", name = "查询用户接口")
    @GetMapping("/queryUser")
    public R<String> queryUser(@RequestParam(required = false) Long id){

        return R.success("查询用户成功");
    }
}

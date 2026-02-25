package com.springcloud.ms.controller.user;

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

    @GetMapping("/queryUser")
    public R<String> queryUser(@RequestParam Long id){

    }
}

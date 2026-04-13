package com.springcloud.ms.controller.user;

import com.springcloud.ms.annotation.LogExecutionTime;
import com.springcloud.ms.controller.pattern.R;
import com.springcloud.ms.entity.User;
import com.springcloud.ms.pojo.dto.AddUserDto;
import com.springcloud.ms.pojo.dto.UpdateUserDto;
import com.springcloud.ms.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author yaorp
 */
@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @LogExecutionTime(value = "queryUser", name = "查询用户接口")
    @GetMapping("/queryUser")
    public R<User> queryUser(@RequestParam String id){
        User user = userService.qryUserById(id);
        return R.success(user);
    }

    @LogExecutionTime(value = "saveUser", name = "新增用户接口")
    @PostMapping("/saveUser")
    public R<String> saveUser(@RequestBody AddUserDto dto){
        userService.addOne(dto);
        return R.success("新增成功");
//            return R.success(null);
    }

    @LogExecutionTime(value = "deleteUser", name = "删除用户接口")
    @GetMapping("/deleteUser")
    public R<String> deleteUser(@RequestParam String id){
        userService.delete(id);
        return R.success("删除成功");
    }

    @LogExecutionTime(value = "updateUser", name = "删除用户接口")
    @PostMapping("/updateUser")
    public R<String> updateUser(@RequestBody UpdateUserDto dto){
        userService.update(dto);
        return R.success("删除成功");
    }
}

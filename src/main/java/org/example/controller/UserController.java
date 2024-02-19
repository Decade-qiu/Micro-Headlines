package org.example.controller;

import io.micrometer.common.util.StringUtils;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utils.JwtUtil;
import org.example.utils.Result;
import org.example.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody User user) {
        Result<Map<String, String>> result = userService.login(user);
        return result;
    }

    @GetMapping("/getUserInfo")
    public Result<Map<String, User>> getUserInfo(@RequestHeader String token) {
        return userService.getUserInfo(token);
    }

    @PostMapping("/checkUserName")
    public Result<Map<String, String>> checkUserName(String username) {
        return userService.checkUserName(username);
    }

    @PostMapping("/regist")
    public Result<Map<String, String>> regist(@RequestBody User user) {
        return userService.regist(user);
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        if (StringUtils.isEmpty(token) || jwtUtil.isExpiration(token)){
            //没有传或者过期 未登录
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        return Result.ok(null);
    }
}

package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.common.util.StringUtils;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.mapper.UserMapper;
import org.example.utils.JwtUtil;
import org.example.utils.MD5Util;
import org.example.utils.Result;
import org.example.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Decade王小明
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2024-02-18 17:45:45
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private JwtUtil jwtHelper;
    @Autowired
    private UserMapper userMapper;

    public Result<Map<String, String>> login(User user) {
        User loginUser = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())
        );

        if (loginUser == null) {
            // 账号错误
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        // 判断密码
        if (!StringUtils.isEmpty(user.getUserPwd())
                && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))) {
            // 账号密码正确
            // 根据用户唯一标识生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

            Map<String, String> data = new HashMap<>();
            data.put("token", token);

            return Result.ok(data);
        }

        // 密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result<Map<String, User>> getUserInfo(String token) {
        // if (StringUtils.isEmpty(token)) {
        //     return Result.build(null, ResultCodeEnum.NOTLOGIN);
        // }
        // boolean isExpiration = jwtHelper.isExpiration(token);
        // if (isExpiration) {
        //     return Result.build(null, ResultCodeEnum.NOTLOGIN);
        // }
        Integer userId = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUid, userId)
        );
        user.setUserPwd("");
        Map<String, User> data = new HashMap<>();
        data.put("loginUser", user);
        return Result.ok(data);
    }

    @Override
    public Result<Map<String, String>> checkUserName(String username) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
        );
        if (user != null) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }

    @Override
    public Result<Map<String, String>> regist(User user) {
        if (checkUserName(user.getUsername()).getCode().equals(ResultCodeEnum.USERNAME_USED.getCode())) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }
}





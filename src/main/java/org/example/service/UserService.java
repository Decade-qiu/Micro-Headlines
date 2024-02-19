package org.example.service;

import org.example.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.utils.Result;

import java.util.Map;

/**
* @author Decade王小明
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-02-18 17:45:45
*/
public interface UserService extends IService<User> {
    Result<Map<String, String>> login(User user);
    Result<Map<String, User>> getUserInfo(String token);
    Result<Map<String, String>> checkUserName(String username);
    Result<Map<String, String>> regist(User user);
}

package org.example;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.mapper.HeadlineMapper;
import org.example.pojo.Portal;
import org.example.service.HeadlineService;
import org.example.utils.JwtUtil;
import org.example.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class DBTest {
    @Autowired
    private HeadlineService headlineService;
    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtUtil jwtHelper;

    @Test
    public void DB() {
        Result<Map<String, Object>> data = headlineService.showHeadlineDetail(1);
        System.out.println("data = " + data);
    }

    @Test
    public void Jwt() {
        // 生成 传入用户标识
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);

        // 解析用户标识
        int userId = jwtHelper.getUserId(token).intValue();
        System.out.println("userId = " + userId);

        // 校验是否到期! false 未到期 true到期
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }


    @Test
    public void EnumTest() {
        ColorEnum color = ColorEnum.GREEN;
        System.out.println(color.name()+" "+color.ordinal());
    }
}



package org.example.service;

import org.example.pojo.Portal;
import org.example.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.utils.Result;

import java.util.List;
import java.util.Map;

/**
* @author Decade王小明
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-02-18 17:45:45
*/
public interface TypeService extends IService<Type> {
    Result<List<Type>> findAllTypes();
    Result<Map<String, Object>> findNewsPage(Portal portal);
}

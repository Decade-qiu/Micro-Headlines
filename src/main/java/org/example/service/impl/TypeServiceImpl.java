package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.HeadlineMapper;
import org.example.pojo.Portal;
import org.example.pojo.Type;
import org.example.service.TypeService;
import org.example.mapper.TypeMapper;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @author Decade王小明
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-02-18 17:45:45
*/
@Service
@Transactional
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result<List<Type>> findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }

    @Override
    public Result<Map<String, Object>> findNewsPage(Portal portal) {
        return null;
    }
}





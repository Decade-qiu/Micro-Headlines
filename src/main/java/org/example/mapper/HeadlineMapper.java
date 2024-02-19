package org.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.pojo.Portal;

import java.util.List;
import java.util.Map;

/**
* @author Decade王小明
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-02-18 17:45:45
* @Entity org.example.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map<String, Object>> findNewsPage(@Param("page") IPage<Map<String, Object>> page, @Param("portal") Portal portal);


    Map<String, Object> selectDetailMap(Integer hid);
}





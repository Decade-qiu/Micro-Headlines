package org.example.service;

import org.example.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.Portal;
import org.example.utils.Result;

import java.util.Map;

/**
* @author Decade王小明
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-02-18 17:45:45
*/
public interface HeadlineService extends IService<Headline> {
    Result<Map<String, Object>> findNewsPage(Portal portal);

    Result<Map<String, Object>> showHeadlineDetail(Integer hid);

    Result<Map<String, Object>> publish(Headline headline, String token);

    Result<Map<String, Object>> update(Headline headline);
}

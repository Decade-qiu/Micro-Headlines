package org.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.pojo.Headline;
import org.example.pojo.Portal;
import org.example.service.HeadlineService;
import org.example.mapper.HeadlineMapper;
import org.example.utils.JwtUtil;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author Decade王小明
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-02-18 17:45:45
*/
@Service
@Transactional
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result<Map<String, Object>> findNewsPage(Portal portal) {
        IPage<Map<String, Object>> page = new Page<>(portal.getPageNum(), portal.getPageSize());
        headlineMapper.findNewsPage(page, portal);
        Map<String, Object> data = new HashMap<>();
        data.put("pageNum", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("totalSize", page.getTotal());
        data.put("totalPage", page.getPages());
        data.put("pageData", page.getRecords());
        Map<String, Object> result = new HashMap<>();
        result.put("pageInfo", data);
        return Result.ok(result);
    }

    @Override
        public Result<Map<String, Object>> showHeadlineDetail(Integer hid){
        Map<String, Object> detailMap = headlineMapper.selectDetailMap(hid);
        Map<String, Object> data = new HashMap<>();
        data.put("headline", detailMap);
        // version更新 乐观锁
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setVersion((Integer) detailMap.get("version"));
        headline.setPageViews((Integer) detailMap.get("pageViews") + 1);
        headlineMapper.updateById(headline);
        return Result.ok(data);
    }

    @Override
    public Result<Map<String, Object>> publish(Headline headline, String token) {
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headline.setPublisher(jwtUtil.getUserId(token).intValue());
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result<Map<String, Object>> update(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }
}





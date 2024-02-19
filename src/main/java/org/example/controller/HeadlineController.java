package org.example.controller;

import org.example.pojo.Headline;
import org.example.service.HeadlineService;
import org.example.utils.Result;
import org.example.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/headline")
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("/publish")
    public Result<Map<String, Object>> publish(@RequestBody Headline headline, @RequestHeader String token) {
        Result<Map<String, Object>> result = headlineService.publish(headline, token);
        return result;
    }

    @PostMapping("/findHeadlineByHid")
    public Result<Map<String, Object>> findHeadlineByHid(Integer hid) {
        Headline result = headlineService.getById(hid);
        Map<String, Object> data = new HashMap<>();
        data.put("headline", result);
        return Result.ok(data);
    }

    @PostMapping("/update")
    public Result<Map<String, Object>> update(@RequestBody Headline headline) {
        Result<Map<String, Object>> result = headlineService.update(headline);
        return result;
    }

    @PostMapping("/removeByHid")
    public Result<Map<String, Object>> removeByHid(Integer hid) {
        boolean result = headlineService.removeById(hid);
        return result ? Result.ok(null) : Result.build(null, 500, "删除失败");
    }
}

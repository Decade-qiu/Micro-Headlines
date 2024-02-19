package org.example.controller;

import org.example.pojo.Headline;
import org.example.pojo.Portal;
import org.example.pojo.Type;
import org.example.service.HeadlineService;
import org.example.service.TypeService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/portal")
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("/publish")
    public Result<Map<String, Object>> publish(@RequestBody Headline headline, @RequestHeader String token) {
        Result<Map<String, Object>> result = headlineService.publish(headline, token);
        return result;
    }

    @GetMapping("findAllTypes")
    public Result<List<Type>> findAllTypes() {
        Result<List<Type>> result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result<Map<String, Object>> findNewsPage(@RequestBody Portal portal) {
        Result<Map<String, Object>> result = headlineService.findNewsPage(portal);
        return result;
    }

    @PostMapping("/showHeadlineDetail")
    public Result<Map<String, Object>> showHeadlineDetail(Integer hid) {
        Result<Map<String, Object>> result = headlineService.showHeadlineDetail(hid);
        return result;
    }

}

package com.goxiaoge.turingsociety.controller;

import com.goxiaoge.turingsociety.pojo.Application;
import com.goxiaoge.turingsociety.utils.result.HttpResult;
import com.goxiaoge.turingsociety.utils.result.Result;
import com.goxiaoge.turingsociety.service.ApplicationService;
import com.goxiaoge.turingsociety.utils.chainbuilder.StringChainBuilder;
import org.apache.coyote.Request;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class ApplicationController {

    @Resource
    ApplicationService service;

    /**
     * @return 存入成功返回 id，失败返回 -1
     */
    @PostMapping("/application")
    public Result<Long> apply(@RequestBody Application application) {
        // 强制 id 为 null, 使得前端无法自定义 id, id 依靠雪花算法生成
        application.setId(null);
        return new StringChainBuilder<>(HttpResult.normativeError(-1L))
                .quickBuilder(application)
                .then(() -> service.save(application), HttpResult.serviceError(-1L))
                .end(HttpResult.success(application.getId()));
    }

    @GetMapping("/application/{id}")
    public Result<Application> getApplicationById(@PathVariable Long id) {
        return HttpResult.success(service.getById(id));
    }

    @PatchMapping("/application/{id}")
    public Result<Boolean> updateApplication(@RequestBody Application application,
                                             @PathVariable Long id) {
        application.setId(id);
        return new StringChainBuilder<>(HttpResult.normativeError(false), true)
                .quickBuilder(application)
                .then(() -> service.updateById(application), HttpResult.serviceError(false))
                .end(HttpResult.success(true));
    }

}

package com.goxiaoge.turingsociety.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {

    @RequestMapping("/test/{msg}")
    public String test(@PathVariable String msg) {
        return LocalDateTime.now() + ": " + msg;
    }

}

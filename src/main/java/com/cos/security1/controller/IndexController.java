package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"","/"})
    public String index(){
        // mustache 기본 폴더: src/main/resources/
        // 뷰리졸버 설정: templates (prefix).mustache(suffix) (기본설정 -> 생략가능)
        return "index"; // src/main/resources/templates/index.mustache 탐색
    }
}

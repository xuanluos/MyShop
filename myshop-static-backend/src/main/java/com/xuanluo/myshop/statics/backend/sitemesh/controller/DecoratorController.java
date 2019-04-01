package com.xuanluo.myshop.statics.backend.sitemesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "decorator")
public class DecoratorController {
    @RequestMapping(value = "default")
    public String defaultDecorator() {
        return "decorator/default";
    }
}

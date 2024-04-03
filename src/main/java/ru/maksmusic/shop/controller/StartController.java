package ru.maksmusic.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping("/index")
    public String start(){
        return "index";
    }
}

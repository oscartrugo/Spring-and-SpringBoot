package com.example.springboot.error.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @SuppressWarnings("unused") //Suprime los warning de valores que no estén siendo utilizados
    @GetMapping("/index")
    public String index(){
        Integer valor = 100/0;
        //Integer valor = Integer.parseInt("10xaaaa");
        return "index";
    }
}

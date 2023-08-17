package com.example.springboot.horario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping({"/", "index"})
    public String index(Model model){
        model.addAttribute("titulo", "Bienvenido al horario de atencion a clientes"); //Pasamos un mensaje a la vista.
        return "index";
    }
}

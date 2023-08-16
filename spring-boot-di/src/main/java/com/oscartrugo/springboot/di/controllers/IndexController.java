package com.oscartrugo.springboot.di.controllers;

import com.oscartrugo.springboot.di.models.service.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    @Qualifier("miServicioComplejo")
    private IServicio servicio;

    @GetMapping({"/", "index", ""})
    public String index(Model model){
        model.addAttribute("objeto", servicio.operacion()); //Le pasamos a la vista el dato que buscamos
        return "index";
    }

}

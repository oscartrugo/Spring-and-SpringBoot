package com.bolsadeideas.springboot.web.app.controllers;

import com.bolsadeideas.springboot.web.app.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller //Componente Spring tipo Controller
@RequestMapping("/app")
public class IndexController {

    @Value("${texto.indexcontroller.index.titulo}")
    private String textoIndex;
    @Value("${texto.indexcontroller.perfil.titulo}")
    private String textoPerfil;
    @Value("${texto.indexcontroller.listar.titulo}")
    private String textoListar;
    //@RequestMapping(value = "/index", method = RequestMethod.GET)
    //@PostMapping(value = "/index")
    @GetMapping({"/index", "/", "/home", ""})
    public String index(Model model){
        model.addAttribute("titulo", textoIndex);
        return "index";
    }

    @RequestMapping("/perfil")
    public String perfil(Model model){

        Usuario usuario = new Usuario();
        usuario.setNombre("Oscar");
        usuario.setApellido("Trujillo");
        usuario.setEmail("oscartrugo@gmail.com");

        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));

        return "perfil";
    }

    @RequestMapping(value = "/listar")
    public String listar (Model model){
        model.addAttribute("titulo", textoListar);
        return "listar";
    }

    @ModelAttribute("usuarios")
    public List<Usuario> poblarUsuarios(){
        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Oscar", "Trujillo", "oscartrugo@gmail.com"),
                new Usuario("Diana", "Colin", "diana@gmail.com"),
                new Usuario("Jorge", "Saavedra", "jorge@gmail.com")
        );

        return usuarios;
    }

}




/*
* Un controlador tiene métodos que manejan peticiones del usuario.
* Ejem: formularios, bases de datos, listar, etc.
*
* Se encarga de manejar las peticiones del usuario, mostrar los datos que el usuario solicita.
* */

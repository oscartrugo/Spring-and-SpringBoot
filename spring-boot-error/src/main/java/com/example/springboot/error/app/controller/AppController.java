package com.example.springboot.error.app.controller;

import com.example.springboot.error.app.errors.UsuarioNoEncontradoException;
import com.example.springboot.error.app.models.domain.Usuario;
import com.example.springboot.error.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {

    @Autowired
    private UsuarioService usuarioService;

    @SuppressWarnings("unused") //Suprime los warning de valores que no estén siendo utilizados
    @GetMapping("/index")
    public String index(){
        Integer valor = 100/0;
        //Integer valor = Integer.parseInt("10xaaaa");
        return "index";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model){
       // Usuario usuario = usuarioService.obtenerPorId(id); //Obtenemos el usuario por el id
        /*if(usuario==null){
            throw new UsuarioNoEncontradoException(id.toString()); //Lanzamos nuestro error personalizado de tipo UsuarioNoEncontrado
        }*/
        Usuario usuario = usuarioService.obtenerPorIdOptional(id).orElseThrow(() -> new UsuarioNoEncontradoException(id.toString())); //Esto es en vez de todo lo anterior
        model.addAttribute("usuario", usuario); //Le pasamos el usuario a la vista
        model.addAttribute("titulo", "Detalle usuario: ".concat(usuario.getNombre()));
        return "ver";
    }
}

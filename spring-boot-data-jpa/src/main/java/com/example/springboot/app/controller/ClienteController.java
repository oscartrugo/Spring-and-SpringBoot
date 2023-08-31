package com.example.springboot.app.controller;

import com.example.springboot.app.models.entity.Cliente;
import com.example.springboot.app.models.service.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("cliente") //Indicamos que se guarda en los atributos de la sesión el objeto cliente mapeado al formulario
public class ClienteController {

    @Autowired //Busca un componente registrado en el contenedor que implemente IClienteDao
    private IClienteService clienteService;

    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteService.findAll());
        return "listar";
    }

    @GetMapping("/form")
    public String crear(Map<String, Object> model){

        Cliente cliente = new Cliente();

        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");

        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model){

        Cliente cliente = null;

        if(id>0){
            cliente = clienteService.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar cliente");

        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model, SessionStatus status){ //Habilitamos la validación para que se valide el cliente //Recibe el objeto Cliente que viene con los datos poblados del formulario
        if(result.hasErrors()){ //Si el cliente tiene errores ...
            model.addAttribute("titulo", "Formulario de Cliente"); //El cliente se pasa en automático si se llaman igual
            return "form"; //Retornamos la vista con los errores
        }
        clienteService.save(cliente); //Los guardamos
        status.setComplete(); //Elimina el objeto cliente de la sesión y termina el proceso de editar o crear
        return "redirect:listar"; //Redirigimos a listar usuarios
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){
        if(id>0){
            clienteService.delete(id);
        }
        return "redirect:/listar";
    }

}

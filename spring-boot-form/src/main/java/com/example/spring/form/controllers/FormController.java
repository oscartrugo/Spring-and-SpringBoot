package com.example.spring.form.controllers;

import com.example.spring.form.editors.NombreMayusculaEditor;
import com.example.spring.form.editors.PaisPropertyEditor;
import com.example.spring.form.editors.RolesEditor;
import com.example.spring.form.models.domain.Pais;
import com.example.spring.form.models.domain.Role;
import com.example.spring.form.models.domain.Usuario;
import com.example.spring.form.services.PaisService;
import com.example.spring.form.services.RoleService;
import com.example.spring.form.validation.UsuarioValidador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@SessionAttributes("usuario") //Le pasamos el nombre del objeto que se pasa a la vista del controller, todos los datos independientes se mantienen de forma persistente
public class FormController {

    @Autowired
    private UsuarioValidador validador;

    @Autowired
    private PaisService paisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PaisPropertyEditor paisEditor;

    @Autowired
    private RolesEditor roleEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(validador); //Inyectamos el validador y lo pasamos por argumento
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); //Define si el analizador de la fecha es estricto o es tolerante al interpretar el patrón, convierte el formato si es incorrecto, false = estricto
        binder.registerCustomEditor(Date.class, "fechaNacimiento",new CustomDateEditor(dateFormat, true));

        binder.registerCustomEditor(String.class, "nombre",new NombreMayusculaEditor()); //field es el campo mapeado a pasar el filtro
        binder.registerCustomEditor(String.class, "apellido",new NombreMayusculaEditor());

        binder.registerCustomEditor(Pais.class, "pais", paisEditor);
        binder.registerCustomEditor(Role.class, "roles", roleEditor);
    }

    @ModelAttribute("listaGenero")
    public List<String> genero(){
        return Arrays.asList("Hombre", "Mujer");
    }

    @ModelAttribute("listaRoles")
    public List<Role> listaRoles(){
        return this.roleService.listar();
    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises(){
        return paisService.listar();
    }

    @ModelAttribute("listaRolesString")
    public List<String> listaRolesString(){
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_MODERATOR");
        return roles;
    }

    @ModelAttribute("listaRolesMap")
    public Map<String, String> listaRolesMap(){
        Map<String, String> roles = new HashMap<>();
        roles.put("ROLE_ADMIN","Administrador");
        roles.put("ROLE_USER", "Usuario");
        roles.put("ROLE_MODERATOR", "Moderador");
        return roles;
    }


    @ModelAttribute("paises")
    public List<String> paises(){
        return Arrays.asList("España","Mexico","Chile","Argentina","Peru","Colombia","Venezuela");
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap(){
        Map<String, String> paises = new HashMap<>();
        paises.put("ES","España");
        paises.put("MX", "Mexico");
        paises.put("CL", "Chile");
        paises.put("AR", "Argentina");
        paises.put("PE", "Peru");
        paises.put("CO", "Colombia");
        paises.put("VE", "Venezuela");
        return paises;
    }

    @GetMapping("/form")
    public String form(Model model){
        Usuario usuario = new Usuario();
        usuario.setNombre("Oscar");
        usuario.setApellido("Trujillo");
        usuario.setIdentificador("13.456.789-K");
        usuario.setHabilitar(true);
        usuario.setEmail("yellowgoat.session@gmail.com");
        usuario.setUsername("oscartg");
        usuario.setValorSecreto("Algun valor secreto ****");
        usuario.setPais( new Pais(2, "MX", "Mexico"));
        usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model) { //Recibe un usuario para mapear sus atributos en la vista "resultado"
        //validador.validate(usuario, result);
        if(result.hasErrors()){
            model.addAttribute("titulo", "Resultado Form");//Antes de pasarlo a la bd, validamos que no arroje errores con BindingResult
            System.out.println("Ocurrio un error");
            System.out.println(Objects.requireNonNull(result.getFieldError()).getField());
            return "form";
        }
        return "redirect:/ver"; //Redirige a la vista "ver"
    }

    @GetMapping("/ver")
    public String ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model, SessionStatus status){
        if(usuario==null){ //Si no hay usuario...
            return "redirect:/form"; //Redirige al formulario
        }
        model.addAttribute("titulo", "Resultado Form");
        status.setComplete(); //Se elimina el objeto usuario de la sesion
        return "resultado";
    }

}

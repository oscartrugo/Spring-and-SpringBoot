package com.example.spring.form.validation;

import com.example.spring.form.models.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component //Marcamos como Component para poder inyectarlo al controlador
public class UsuarioValidador implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz); //Si la clase Usuario es asignable a clazz...
    }

    @Override
    public void validate(Object target, Errors errors) {
        //Usuario usuario = (Usuario) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre"); //Validamos si el campo "nombre" es , y arroja un mensaje de error

    }
}

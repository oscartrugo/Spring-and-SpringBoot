package com.example.springboot.error.app.errors;

public class UsuarioNoEncontradoException extends RuntimeException{
    public UsuarioNoEncontradoException(String id) {
        super("Usuario con id: ".concat(id).concat(" no existe en el sistema."));

    }
}

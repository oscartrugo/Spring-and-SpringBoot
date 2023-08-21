package com.example.springboot.error.app.service;

import com.example.springboot.error.app.models.domain.Usuario;

import java.util.List;

public interface UsuarioService {
    public List<Usuario> listar();
    public Usuario obtenerPorId(Integer id);
}

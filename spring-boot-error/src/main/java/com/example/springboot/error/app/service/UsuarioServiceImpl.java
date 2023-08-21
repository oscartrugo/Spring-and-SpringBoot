package com.example.springboot.error.app.service;

import com.example.springboot.error.app.models.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private List<Usuario> lista;

    public UsuarioServiceImpl(List<Usuario> lista) {
        this.lista = new ArrayList<>();
        this.lista.add(new Usuario(1, "Oscar", "Trujillo"));
        this.lista.add(new Usuario(2, "Pepa", "Garcia"));
        this.lista.add(new Usuario(3, "Lalo", "Mena"));
        this.lista.add(new Usuario(4, "Luci", "Fernandez"));
        this.lista.add(new Usuario(5, "Pato", "Gonzalez"));
        this.lista.add(new Usuario(6, "Paco", "Rodriguez"));
        this.lista.add(new Usuario(7, "Juan", "Gomez"));
    }

    @Override
    public List<Usuario> listar() {
        return this.lista;
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        Usuario resultado = null;
        for(Usuario usuario: this.lista){
            if(usuario.getId().equals(id)){
                resultado = usuario;
                break;
            }
        }
        return resultado;
    }
}

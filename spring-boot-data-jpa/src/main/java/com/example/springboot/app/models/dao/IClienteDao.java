package com.example.springboot.app.models.dao;

import com.example.springboot.app.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {
    public List<Cliente> findAll();
    public void save(Cliente cliente);

}

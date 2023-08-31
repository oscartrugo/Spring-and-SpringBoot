package com.example.springboot.app.models.service;

import com.example.springboot.app.models.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClienteService {
    public List<Cliente> findAll();
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);
}

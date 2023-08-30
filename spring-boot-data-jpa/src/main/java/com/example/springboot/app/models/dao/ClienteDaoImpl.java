package com.example.springboot.app.models.dao;

import com.example.springboot.app.models.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true) //Marcamos al método como transactional sólo de lectura
    @Override
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) { //Aqu{i editamos también los clientes existentes
        if(cliente.getId() != null && cliente.getId() > 0){ //si el id es distinto de nulo y mayor que cero ...
            entityManager.merge(cliente); //Editamos el cliente actual. actualiza los datos existentes, Lo atacha al contexto y lo actualiza
        }else{
            entityManager.persist(cliente); //Crea un nuevo cliente, lo inserta y lo guarda en el contexto de persistencia
        }
    }

    @Override
    public Cliente findOne(Long id) {
        return entityManager.find(Cliente.class, id); //JPA va a la base de datos y nos regresa el objeto Cliente
    }
}

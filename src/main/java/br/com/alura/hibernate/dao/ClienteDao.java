package br.com.alura.hibernate.dao;

import br.com.alura.hibernate.model.Cliente;

import javax.persistence.EntityManager;

public class ClienteDao {
    private EntityManager entityManager;

    public ClienteDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addCliente(Cliente cliente) {
        entityManager.persist(cliente);
    }

    public Cliente getBuscaPorId(Long id) {
        return entityManager.find(Cliente.class, id);
    }
}

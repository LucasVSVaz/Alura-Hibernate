package br.com.alura.hibernate.dao;

import br.com.alura.hibernate.model.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {
    private EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addCategoria(Categoria categoria) {
        entityManager.persist(categoria);
    }
}

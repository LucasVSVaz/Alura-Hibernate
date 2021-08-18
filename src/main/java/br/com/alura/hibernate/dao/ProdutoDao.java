package br.com.alura.hibernate.dao;

import br.com.alura.hibernate.model.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {
    private EntityManager entityManager;

    public ProdutoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addProduto(Produto produto) {
        entityManager.persist(produto);
    }

    public List<Produto> getListaProduto() {
        String jpql = "SELECT p FROM Produto p";
        return entityManager.createQuery(jpql).getResultList();
    }

    public Produto getBuscarPorId(Long id) {
        return entityManager.find(Produto.class, id);
    }

    public BigDecimal getBuscaPrecoProNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return entityManager.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
    }

    public BigDecimal getValorTotalVendido() {
        String jpql = "SELECT SUM (p.valorTotal) FROM Pedido p";
        return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

}

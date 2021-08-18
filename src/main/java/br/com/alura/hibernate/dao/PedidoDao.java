package br.com.alura.hibernate.dao;

import br.com.alura.hibernate.model.Pedido;
import br.com.alura.hibernate.model.Produto;
import br.com.alura.hibernate.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {
    private EntityManager entityManager;

    public PedidoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addPedido(Pedido pedido) {
        entityManager.persist(pedido);
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas() {
        String jpql = new StringBuilder()
                .append("SELECT new br.com.alura.hibernate.vo.RelatorioDeVendasVo (")
                .append(" produto.nome,")
                .append(" SUM (item.quantidade)) ")
                .append(" FROM Pedido pedido ")
                .append(" JOIN pedido.itens item")
                .append(" JOIN item.produto produto")
                .append(" GROUP BY produto.nome")
                .append(" ORDER BY item.quantidade DESC").toString();
        return entityManager.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
    }

    public Pedido buscarPedidoComCliente(Long id) {
        return entityManager.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();  /*  Vincular os dados do cliente, mesmo após o fechamento da conexão */
    }


    public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = builder.and();
        if (nome != null && !nome.trim().isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }
        if (preco != null) {
            filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
        }
        query.where(filtros);

        return entityManager.createQuery(query).getResultList();

        /* Buscar na tabela utilizando mais de um filto*/
    }
}

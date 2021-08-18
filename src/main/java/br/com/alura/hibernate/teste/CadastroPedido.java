package br.com.alura.hibernate.teste;

import br.com.alura.hibernate.dao.CategoriaDao;
import br.com.alura.hibernate.dao.ClienteDao;
import br.com.alura.hibernate.dao.PedidoDao;
import br.com.alura.hibernate.dao.ProdutoDao;
import br.com.alura.hibernate.factory.JPAUtil;
import br.com.alura.hibernate.model.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroPedido {
    public static void main(String[] args) {

        cadastrarProduto();

        EntityManager entityManager = new JPAUtil().getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        Produto produto = produtoDao.getBuscarPorId(1l);
        Produto produto1 = produtoDao.getBuscarPorId(2l);

        ClienteDao clienteDao = new ClienteDao(entityManager);
        Cliente cliente = clienteDao.getBuscaPorId(1l);

        entityManager.getTransaction().begin();


        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        pedido.adicionarItem(new ItemPedido(2, pedido, produto1));

        PedidoDao pedidoDao = new PedidoDao(entityManager);
        pedidoDao.addPedido(pedido);


        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println(pedido.getCliente().getNome());


//        List<RelatorioDeVendasVo> relatorioDeVendas = pedidoDao.relatorioDeVendas();
//        relatorioDeVendas.forEach(System.out::println);


    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto produto = new Produto("XIAOMI", "REDMI", new BigDecimal("800.0"), celulares);
        Produto produto1 = new Produto("Samsung", "S20", new BigDecimal("1800.0"), celulares);
        Cliente cliente = new Cliente("Lucas", "08163719621");


        EntityManager entityManager = new JPAUtil().getEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        ClienteDao clienteDao = new ClienteDao(entityManager);


        entityManager.getTransaction().begin();
        categoriaDao.addCategoria(celulares);
        produtoDao.addProduto(produto);
        produtoDao.addProduto(produto1);
        clienteDao.addCliente(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

package br.com.alura.hibernate.vo;

public class RelatorioDeVendasVo {
    private String nomeProduto;
    private Long quantidadeVendida;

    public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                '}';
    }
}

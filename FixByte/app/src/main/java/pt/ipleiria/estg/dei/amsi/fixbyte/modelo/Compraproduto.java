package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import java.util.Date;

public class Compraproduto {

    private long compra_idcompras;
    private long produto_idprodutos;
    private Float produto_preco;

    public Compraproduto (long compra_idcompras, long produto_idprodutos, Float produto_preco)
    {
        this.compra_idcompras = compra_idcompras;
        this.produto_idprodutos = produto_idprodutos;
        this.produto_preco = produto_preco;
    }

    public long getCompra_idcompras() {
        return compra_idcompras;
    }

    public void setCompra_idcompras(long compra_idcompras) {
        this.compra_idcompras = compra_idcompras;
    }

    public long getProduto_idprodutos() {
        return produto_idprodutos;
    }

    public void setProduto_idprodutos(long produto_idprodutos) {
        this.produto_idprodutos = produto_idprodutos;
    }

    public Float getProduto_preco() {
        return produto_preco;
    }

    public void setProduto_preco(Float produto_preco) {
        this.produto_preco = produto_preco;
    }
}


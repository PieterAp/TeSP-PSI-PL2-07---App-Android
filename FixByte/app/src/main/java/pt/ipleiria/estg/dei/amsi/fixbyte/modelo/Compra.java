package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Compra {

    private long idprodutos;
    private Double produto_preco;
    private String produtoNome;
    private String produtoImagem1;
    private String produtoImagem2;
    private String produtoImagem3;
    private String produtoImagem4;

    public Compra(long idprodutos, Double produto_preco, String produtoNome, String produtoImagem1, String produtoImagem2, String produtoImagem3, String produtoImagem4) {
        this.idprodutos = idprodutos;
        this.produto_preco = produto_preco;
        this.produtoNome = produtoNome;
        this.produtoImagem1 = produtoImagem1;
        this.produtoImagem2 = produtoImagem2;
        this.produtoImagem3 = produtoImagem3;
        this.produtoImagem4 = produtoImagem4;
    }

    public long getIdprodutos() {
        return idprodutos;
    }

    public void setIdprodutos(long idprodutos) {
        this.idprodutos = idprodutos;
    }

    public Double getProduto_preco() {
        return produto_preco;
    }

    public void setProduto_preco(Double produto_preco) {
        this.produto_preco = produto_preco;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public String getProdutoImagem1() {
        return produtoImagem1;
    }

    public void setProdutoImagem1(String produtoImagem1) {
        this.produtoImagem1 = produtoImagem1;
    }

    public String getProdutoImagem2() {
        return produtoImagem2;
    }

    public void setProdutoImagem2(String produtoImagem2) {
        this.produtoImagem2 = produtoImagem2;
    }

    public String getProdutoImagem3() {
        return produtoImagem3;
    }

    public void setProdutoImagem3(String produtoImagem3) {
        this.produtoImagem3 = produtoImagem3;
    }

    public String getProdutoImagem4() {
        return produtoImagem4;
    }

    public void setProdutoImagem4(String produtoImagem4) {
        this.produtoImagem4 = produtoImagem4;
    }
}

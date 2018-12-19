package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

public class Produto {

    private long idprodutos;
    private String produtoNome;
    private String produtoCodigo;
    private Integer produtoStock;
    private Float produtoPreco;
    private String produtoMarca;
    private long categoria_child_id;
    private String produtoDescricao1;
    private String produtoDescricao2;
    private String produtoDescricao3;
    private String produtoDescricao4;
    private String produtoDescricao5;
    private String produtoDescricao6;
    private String produtoDescricao7;
    private String produtoDescricao8;
    private String produtoDescricao9;
    private String produtoDescricao10;

    private String produtoImagem1;
    private String produtoImagem2;
    private String produtoImagem3;
    private String produtoImagem4;

    private Integer produtoEstado;


    public Produto(long idprodutos, String produtoNome, String produtoCodigo, Integer produtoStock, Float produtoPreco, String produtoMarca, long categoria_child_id, String produtoDescricao1, String produtoDescricao2, String produtoDescricao3, String produtoDescricao4, String produtoDescricao5, String produtoDescricao6, String produtoDescricao7, String produtoDescricao8, String produtoDescricao9, String produtoDescricao10, String produtoImagem1, String produtoImagem2, String produtoImagem3, String produtoImagem4, Integer produtoEstado) {
        this.idprodutos = idprodutos;
        this.produtoNome = produtoNome;
        this.produtoCodigo = produtoCodigo;
        this.produtoStock = produtoStock;
        this.produtoPreco = produtoPreco;
        this.produtoMarca = produtoMarca;
        this.categoria_child_id = categoria_child_id;
        this.produtoDescricao1 = produtoDescricao1;
        this.produtoDescricao2 = produtoDescricao2;
        this.produtoDescricao3 = produtoDescricao3;
        this.produtoDescricao4 = produtoDescricao4;
        this.produtoDescricao5 = produtoDescricao5;
        this.produtoDescricao6 = produtoDescricao6;
        this.produtoDescricao7 = produtoDescricao7;
        this.produtoDescricao8 = produtoDescricao8;
        this.produtoDescricao9 = produtoDescricao9;
        this.produtoDescricao10 = produtoDescricao10;
        this.produtoImagem1 = produtoImagem1;
        this.produtoImagem2 = produtoImagem2;
        this.produtoImagem3 = produtoImagem3;
        this.produtoImagem4 = produtoImagem4;
        this.produtoEstado = produtoEstado;
    }

    public long getIdprodutos() {
        return idprodutos;
    }

    public void setIdprodutos(long idprodutos) {
        this.idprodutos = idprodutos;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public String getProdutoCodigo() {
        return produtoCodigo;
    }

    public void setProdutoCodigo(String produtoCodigo) {
        this.produtoCodigo = produtoCodigo;
    }

    public Integer getProdutoStock() {
        return produtoStock;
    }

    public void setProdutoStock(Integer produtoStock) {
        this.produtoStock = produtoStock;
    }

    public Float getProdutoPreco() {
        return produtoPreco;
    }

    public void setProdutoPreco(Float produtoPreco) {
        this.produtoPreco = produtoPreco;
    }

    public String getProdutoMarca() {
        return produtoMarca;
    }

    public void setProdutoMarca(String produtoMarca) {
        this.produtoMarca = produtoMarca;
    }

    public long getCategoria_child_id() {
        return categoria_child_id;
    }

    public void setCategoria_child_id(long categoria_child_id) {
        this.categoria_child_id = categoria_child_id;
    }

    public String getProdutoDescricao1() {
        return produtoDescricao1;
    }

    public void setProdutoDescricao1(String produtoDescricao1) {
        this.produtoDescricao1 = produtoDescricao1;
    }

    public String getProdutoDescricao2() {
        return produtoDescricao2;
    }

    public void setProdutoDescricao2(String produtoDescricao2) {
        this.produtoDescricao2 = produtoDescricao2;
    }

    public String getProdutoDescricao3() {
        return produtoDescricao3;
    }

    public void setProdutoDescricao3(String produtoDescricao3) {
        this.produtoDescricao3 = produtoDescricao3;
    }

    public String getProdutoDescricao4() {
        return produtoDescricao4;
    }

    public void setProdutoDescricao4(String produtoDescricao4) {
        this.produtoDescricao4 = produtoDescricao4;
    }

    public String getProdutoDescricao5() {
        return produtoDescricao5;
    }

    public void setProdutoDescricao5(String produtoDescricao5) {
        this.produtoDescricao5 = produtoDescricao5;
    }

    public String getProdutoDescricao6() {
        return produtoDescricao6;
    }

    public void setProdutoDescricao6(String produtoDescricao6) {
        this.produtoDescricao6 = produtoDescricao6;
    }

    public String getProdutoDescricao7() {
        return produtoDescricao7;
    }

    public void setProdutoDescricao7(String produtoDescricao7) {
        this.produtoDescricao7 = produtoDescricao7;
    }

    public String getProdutoDescricao8() {
        return produtoDescricao8;
    }

    public void setProdutoDescricao8(String produtoDescricao8) {
        this.produtoDescricao8 = produtoDescricao8;
    }

    public String getProdutoDescricao9() {
        return produtoDescricao9;
    }

    public void setProdutoDescricao9(String produtoDescricao9) {
        this.produtoDescricao9 = produtoDescricao9;
    }

    public String getProdutoDescricao10() {
        return produtoDescricao10;
    }

    public void setProdutoDescricao10(String produtoDescricao10) {
        this.produtoDescricao10 = produtoDescricao10;
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

    public Integer getProdutoEstado() {
        return produtoEstado;
    }

    public void setProdutoEstado(Integer produtoEstado) {
        this.produtoEstado = produtoEstado;
    }
}

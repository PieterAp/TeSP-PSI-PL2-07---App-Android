package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import java.util.Date;

public class Categoria {

    private long idcategorias;
    private String categoriaNome;
    private String categoriaDescricao;
    private Integer categoriaEstado;
    private Integer qntProdutos;

    public Categoria (long idcategorias, String categoriaNome, String categoriaDescricao, Integer categoriaEstado, Integer qntProdutos)
    {
        this.idcategorias = idcategorias;
        this.categoriaNome = categoriaNome;
        this.categoriaDescricao = categoriaDescricao;
        this.categoriaEstado = categoriaEstado;
        this.qntProdutos = qntProdutos;
    }

    public long getIdcategorias() {
        return idcategorias;
    }

    public void setIdcategorias(long idcategorias) {
        this.idcategorias = idcategorias;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getCategoriaDescricao() {
        return categoriaDescricao;
    }

    public void setCategoriaDescricao(String categoriaDescricao) {
        this.categoriaDescricao = categoriaDescricao;
    }

    public Integer getCategoriaEstado() {
        return categoriaEstado;
    }

    public void setCategoriaEstado(Integer categoriaEstado) {
        this.categoriaEstado = categoriaEstado;
    }

    public Integer getQntProdutos() {
        return qntProdutos;
    }

    public void setQntProdutos(Integer qntProdutos) {
        this.qntProdutos = qntProdutos;
    }
}

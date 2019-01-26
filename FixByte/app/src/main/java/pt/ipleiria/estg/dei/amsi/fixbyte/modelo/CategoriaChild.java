package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

public class CategoriaChild {


    private long idchild;
    private String childNome;
    private String childDescricao;
    private long categoria_idcategorias;
    private Integer childEstado;
    private Integer qntProdutos;

    public CategoriaChild (long idchild, String childNome, String childDescricao, long categoria_idcategorias, Integer childEstado, Integer qntProdutos)
    {
        this.idchild = idchild;
        this.childNome = childNome;
        this.childDescricao = childDescricao;
        this.categoria_idcategorias = categoria_idcategorias;
        this.childEstado = childEstado;
        this.qntProdutos = qntProdutos;
    }

    public long getIdchild() {
        return idchild;
    }

    public void setIdchild(long idchild) {
        this.idchild = idchild;
    }

    public String getChildNome() {
        return childNome;
    }

    public void setChildNome(String childNome) {
        this.childNome = childNome;
    }

    public String getChildDescricao() {
        return childDescricao;
    }

    public void setCategorisaDescricao(String categorisaDescricao) {
        this.childDescricao = categorisaDescricao;
    }

    public long getCategoria_idcategorias() {
        return categoria_idcategorias;
    }

    public void setCategoria_idcategorias(long categoria_idcategorias) {
        this.categoria_idcategorias = categoria_idcategorias;
    }

    public Integer getChildEstado() {
        return childEstado;
    }

    public void setChildEstado(Integer childEstado) {
        this.childEstado = childEstado;
    }

    public Integer getQntProdutos() {
        return qntProdutos;
    }
}

package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

public class CategoriaChild {


    private long idchild;
    private String childNome;
    private String categorisaDescricao;
    private long categoria_idcategorias;
    private Integer childEstado;

    public CategoriaChild (long idchild, String childNome, String categorisaDescricao, long categoria_idcategorias, Integer childEstado)
    {
        this.idchild = idchild;
        this.childNome = childNome;
        this.categorisaDescricao = categorisaDescricao;
        this.categoria_idcategorias = categoria_idcategorias;
        this.childEstado = childEstado;
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

    public String getCategorisaDescricao() {
        return categorisaDescricao;
    }

    public void setCategorisaDescricao(String categorisaDescricao) {
        this.categorisaDescricao = categorisaDescricao;
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
}

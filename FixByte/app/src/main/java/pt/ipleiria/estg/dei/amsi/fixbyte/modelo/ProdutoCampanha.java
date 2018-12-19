package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

public class ProdutoCampanha {

    private long idprodutocampanha;
    private long produtos_idprodutos;
    private long campanha_idCampanha;
    private Integer campanhaPercentagem;

    public ProdutoCampanha(long idprodutocampanha, long produtos_idprodutos, long campanha_idCampanha, Integer campanhaPercentagem) {
        this.idprodutocampanha = idprodutocampanha;
        this.produtos_idprodutos = produtos_idprodutos;
        this.campanha_idCampanha = campanha_idCampanha;
        this.campanhaPercentagem = campanhaPercentagem;
    }

    public long getIdprodutocampanha() {
        return idprodutocampanha;
    }

    public void setIdprodutocampanha(long idprodutocampanha) {
        this.idprodutocampanha = idprodutocampanha;
    }

    public long getProdutos_idprodutos() {
        return produtos_idprodutos;
    }

    public void setProdutos_idprodutos(long produtos_idprodutos) {
        this.produtos_idprodutos = produtos_idprodutos;
    }

    public long getCampanha_idCampanha() {
        return campanha_idCampanha;
    }

    public void setCampanha_idCampanha(long campanha_idCampanha) {
        this.campanha_idCampanha = campanha_idCampanha;
    }

    public Integer getCampanhaPercentagem() {
        return campanhaPercentagem;
    }

    public void setCampanhaPercentagem(Integer campanhaPercentagem) {
        this.campanhaPercentagem = campanhaPercentagem;
    }
}

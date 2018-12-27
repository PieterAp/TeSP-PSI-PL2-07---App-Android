package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;


import java.util.Date;

public class Campanha
{
    private long idCampanha;
    private String campanhaNome;
    private String campanhaDescricao;
    private String campanhaDataInicio;
    private String campanhaDataFim;

    public Campanha (long idCampanha, String campanhaNome, String campanhaDataInicio, String campanhaDescricao, String campanhaDataFim)
    {
        this.idCampanha = idCampanha;
        this.campanhaNome = campanhaNome;
        this.campanhaDataInicio = campanhaDataInicio;
        this.campanhaDescricao = campanhaDescricao;
        this.campanhaDataFim = campanhaDataFim;
    }

    public long getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(long idCampanha) {
        this.idCampanha = idCampanha;
    }

    public String getCampanhaNome() {
        return campanhaNome;
    }

    public void setCampanhaNome(String campanhaNome) {
        this.campanhaNome = campanhaNome;
    }

    public String getCampanhaDescricao() {
        return campanhaDescricao;
    }

    public void setCampanhaDescricao(String campanhaDescricao) {
        this.campanhaDescricao = campanhaDescricao;
    }

    public String getCampanhaDataInicio() {
        return campanhaDataInicio;
    }

    public void setCampanhaDataInicio(String campanhaDataInicio) {
        this.campanhaDataInicio = campanhaDataInicio;
    }

    public String getCampanhaDataFim() {
        return campanhaDataFim;
    }

    public void setCampanhaDataFim(String campanhaDataFim) {
        this.campanhaDataFim = campanhaDataFim;
    }
}

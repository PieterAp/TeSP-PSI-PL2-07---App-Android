package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import java.util.Date;

public class Campanha
{
    private long idCampanha;
    private String campanhaNome;
    private String campanhaDescricao;
    private Date campanhaDataInicio;
    private Date campanhaDataFim;

    public Campanha (long id, String campanhaNome, String campanhaDescricao, Date campanhaDataInicio, Date campanhaDataFim)
    {
        this.idCampanha = id;
        this.campanhaNome = campanhaNome;
        this.campanhaDataInicio = campanhaDataInicio;
        this.campanhaDataFim = campanhaDataFim;
        this.campanhaDescricao = campanhaDescricao;
    }


}

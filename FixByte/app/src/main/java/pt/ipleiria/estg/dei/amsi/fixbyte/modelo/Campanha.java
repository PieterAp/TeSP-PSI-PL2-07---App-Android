package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public String getCampanhaDataFimEnglishParse() {

        SimpleDateFormat format = new SimpleDateFormat("d");

        System.out.println("this is my starting date" + format.toString());

        if(this.campanhaDataFim.endsWith("1") && !this.campanhaDataFim.endsWith("11"))
            format = new SimpleDateFormat("d'st' MMMM");
        else if(this.campanhaDataFim.endsWith("2") && !this.campanhaDataFim.endsWith("12"))
            format = new SimpleDateFormat("d'nd' MMMM");
        else if(this.campanhaDataFim.endsWith("3") && !this.campanhaDataFim.endsWith("13"))
            format = new SimpleDateFormat("d'rd' MMMM");
        else
            format = new SimpleDateFormat("d'th' MMMM");

        SimpleDateFormat formatCampanha = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatCampanha.parse(campanhaDataFim);
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return campanhaDataFim;
        }
    }

    public void setCampanhaDataFim(String campanhaDataFim) {
        this.campanhaDataFim = campanhaDataFim;
    }
}

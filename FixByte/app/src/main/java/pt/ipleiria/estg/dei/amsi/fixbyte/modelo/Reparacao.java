package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reparacao {

    private long idreparacao;
    private String reparacaoNome;
    private String reparacaoEstado;
    private long reparacaoNumero;
    private Date reparacaoData;
    private Date reparacaoDataConcluido;
    private String reparacaoDescricao;
    private long user_iduser;

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");



    public Reparacao(long idreparacao, String reparacaoNome,Date reparacaoData,Date reparacaoDataConcluido, String reparacaoEstado, long reparacaoNumero, String reparacaoDescricao, long user_iduser) {
        this.idreparacao = idreparacao;
        this.reparacaoNome = reparacaoNome;
        this.reparacaoEstado = reparacaoEstado;
        this.reparacaoNumero = reparacaoNumero;
        //Date date = new Date();
        //this.reparacaoData = date;
        //this.reparacaoDataConcluido = date;
        this.reparacaoData = reparacaoData;
        this.reparacaoDataConcluido = reparacaoDataConcluido;
        this.reparacaoDescricao = reparacaoDescricao;
        this.user_iduser = user_iduser;
    }

    public long getIdreparacao() {
        return idreparacao;
    }

    public void setIdreparacao(long idreparacao) {
        this.idreparacao = idreparacao;
    }

    public String getReparacaoNome() {
        return reparacaoNome;
    }

    public void setReparacaoNome(String reparacaoNome) {
        this.reparacaoNome = reparacaoNome;
    }

    public String getReparacaoEstado() {
        return reparacaoEstado;
    }

    public void setReparacaoEstado(String reparacaoEstado) {
        this.reparacaoEstado = reparacaoEstado;
    }

    public long getReparacaoNumero() {
        return reparacaoNumero;
    }

    public void setReparacaoNumero(long reparacaoNumero) {
        this.reparacaoNumero = reparacaoNumero;
    }

    public Date getReparacaoData() {
        return reparacaoData;
    }

    public void setReparacaoData(Date reparacaoData) {
        this.reparacaoData = reparacaoData;
    }

    public Date getReparacaoDataConcluido() {
        return reparacaoDataConcluido;
    }

    public void setReparacaoDataConcluido(Date reparacaoDataConcluido) {
        this.reparacaoDataConcluido = reparacaoDataConcluido;
    }

    public String getReparacaoDescricao() {
        return reparacaoDescricao;
    }

    public void setReparacaoDescricao(String reparacaoDescricao) {
        this.reparacaoDescricao = reparacaoDescricao;
    }

    public long getUser_iduser() {
        return user_iduser;
    }

    public void setUser_iduser(long user_iduser) {
        this.user_iduser = user_iduser;
    }
}

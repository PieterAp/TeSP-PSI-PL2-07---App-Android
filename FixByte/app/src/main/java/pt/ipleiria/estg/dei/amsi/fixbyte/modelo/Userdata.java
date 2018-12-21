package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import java.util.Date;

public class Userdata {

    private long iduser;
    private String userNomeProprio;
    private String userApelido;
    private Integer userNIF;
    private String userDataNasc;
    private String userEstado;
    private String userMorada;
    private long user_id;
    private Integer userVisibilidade;

    public Userdata(long iduser, String userNomeProprio, String userApelido, Integer userNIF, String userDataNasc, String userEstado, String userMorada, long user_id, Integer userVisibilidade) {
        this.iduser = iduser;
        this.userNomeProprio = userNomeProprio;
        this.userApelido = userApelido;
        this.userNIF = userNIF;
        this.userDataNasc = userDataNasc;
        this.userEstado = userEstado;
        this.userMorada = userMorada;
        this.user_id = user_id;
        this.userVisibilidade = userVisibilidade;
    }

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public String getUserNomeProprio() {
        return userNomeProprio;
    }

    public void setUserNomeProprio(String userNomeProprio) {
        this.userNomeProprio = userNomeProprio;
    }

    public String getUserApelido() {
        return userApelido;
    }

    public void setUserApelido(String userApelido) {
        this.userApelido = userApelido;
    }

    public Integer getUserNIF() {
        return userNIF;
    }

    public void setUserNIF(Integer userNIF) {
        this.userNIF = userNIF;
    }

    public String getUserDataNasc() {
        return userDataNasc;
    }

    public void setUserDataNasc(String userDataNasc) {
        this.userDataNasc = userDataNasc;
    }

    public String getUserEstado() {
        return userEstado;
    }

    public void setUserEstado(String userEstado) {
        this.userEstado = userEstado;
    }

    public String getUserMorada() {
        return userMorada;
    }

    public void setUserMorada(String userMorada) {
        this.userMorada = userMorada;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public Integer getUserVisibilidade() {
        return userVisibilidade;
    }

    public void setUserVisibilidade(Integer userVisibilidade) {
        this.userVisibilidade = userVisibilidade;
    }
}

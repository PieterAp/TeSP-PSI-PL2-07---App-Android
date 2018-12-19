package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import java.util.Date;

public class Compra {

    private long idCompra;
    private Date compraData;
    private long user_iduser;
    private Float compraValor;
    private Integer compraEstado;


    public Compra (long idCompra, Date compraData, long user_iduser, Float compraValor, Integer compraEstado)
    {
        this.idCompra = idCompra;
        Date date = new Date();
        this.compraData = date;
        this.user_iduser = user_iduser;
        this.compraValor = compraValor;
        this.compraEstado = compraEstado;

    }

    public long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(long idCompra) {
        this.idCompra = idCompra;
    }

    public Date getCompraData() {
        return compraData;
    }

    public void setCompraData(Date compraData) {
        this.compraData = compraData;
    }

    public long getUser_iduser() {
        return user_iduser;
    }

    public void setUser_iduser(long user_iduser) {
        this.user_iduser = user_iduser;
    }

    public Float getCompraValor() {
        return compraValor;
    }

    public void setCompraValor(Float compraValor) {
        this.compraValor = compraValor;
    }

    public Integer getCompraEstado() {
        return compraEstado;
    }

    public void setCompraEstado(Integer compraEstado) {
        this.compraEstado = compraEstado;
    }
}

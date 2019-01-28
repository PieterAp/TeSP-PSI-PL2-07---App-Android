package pt.ipleiria.estg.dei.amsi.fixbyte.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Compra;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.User;

public interface ComprasListener {
    void onRefreshListaCompra(ArrayList<Compra> compra);
    void callback(boolean state);
}

package pt.ipleiria.estg.dei.amsi.fixbyte.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;

public interface CampanhasListener {
    void onRefreshListaCampanhas(ArrayList<Campanha> listacampanhas);
    void onUpdateListaLivrosBD (Campanha livro, int operacao);
}

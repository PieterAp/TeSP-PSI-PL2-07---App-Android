package pt.ipleiria.estg.dei.amsi.fixbyte.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.User;

public interface FixByteListener {
    void onRefreshListaCampanhas(ArrayList<Campanha> listacampanhas);
    void onUpdateListaCampanhasBD (Campanha livro, int operacao);

    void onRefreshListaProdutosCampanha(ArrayList<ProdutoCampanha> listaprodutoscampanha);
    void onUpdateListaProdutosCampanhaBD (ProdutoCampanha produtocampanha, int operacao);

    void onRefreshListaUsers(ArrayList<User> listausers);
    void onUpdateListaUsersBD (User user, int operacao);
}

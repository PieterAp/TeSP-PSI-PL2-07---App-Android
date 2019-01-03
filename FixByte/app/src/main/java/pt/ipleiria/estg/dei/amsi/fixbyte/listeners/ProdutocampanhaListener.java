package pt.ipleiria.estg.dei.amsi.fixbyte.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;

public interface ProdutocampanhaListener {
    void onRefreshListaProdutosCampanha(ArrayList<ProdutoCampanha> listaprodutoscampanha);
    void onUpdateListaProdutosCampanhaBD (ProdutoCampanha produtocampanha, int operacao);
}

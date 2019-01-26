package pt.ipleiria.estg.dei.amsi.fixbyte.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Categoria;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.CategoriaChild;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.User;

public interface FixByteListener {
    void onRefreshListaCampanhas(ArrayList<Campanha> listacampanhas);
    void onUpdateListaCampanhasBD (Campanha livro, int operacao);

    void onRefreshListaProdutosCampanha(ArrayList<ProdutoCampanha> listaprodutoscampanha);
    void onUpdateListaProdutosCampanhaBD (ProdutoCampanha produtocampanha, int operacao);

    void onRefreshListaCategorias(ArrayList<Categoria> listacategorias);
    void onUpdateListaCategoriasBD (Categoria categoria, int operacao);

    void onRefreshListaCategoriasChild(ArrayList<CategoriaChild> listacategoriasChild);
    void onUpdateListaCategoriasChildBD (CategoriaChild categoriaChild, int operacao);
}

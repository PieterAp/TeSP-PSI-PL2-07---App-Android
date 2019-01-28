package pt.ipleiria.estg.dei.amsi.fixbyte;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.R;
import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaProdutoAdaptador;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.FixByteListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Categoria;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.CategoriaChild;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.FixByteSingleton;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Produto;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Reparacao;
import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements FixByteListener
{
    private ListView lvlistView;
    private Button btnTakeLook;
    private ArrayList<Produto> listaProdutos;
    private ArrayList<Produto> modifiedProduto;
    private ListaProdutoAdaptador listaProdutoAdaptador;
    private Long idParent;

    private CategoriesChildFragment categoriesChildFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        FixByteSingleton.getInstance(getActivity().getApplicationContext()).setFixByteListener(this);
        FixByteSingleton.getInstance(getActivity().getApplicationContext()).getAllProdutosAPI(getActivity().getApplicationContext(),FixByteJsonParser.isConnectedInternet(getActivity().getApplicationContext()));

        lvlistView = (ListView) getView().findViewById(R.id.listVIewProductsHome);


        lvlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Produto tempProduto = (Produto) parent.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra(ProductDetailActivity.DETALHES_PRODUTOS, tempProduto.getIdprodutos());
                startActivity(intent);
            }
        });

    }

    public  void onResume()
    {
        super.onResume();
        FixByteSingleton.getInstance(getActivity().getApplicationContext()).getAllProdutosAPI(getActivity().getApplicationContext(),FixByteJsonParser.isConnectedInternet(getActivity().getApplicationContext()));
    }



    @Override
    public void onRefreshListaCampanhas(ArrayList<Campanha> listacampanhas) {

    }

    @Override
    public void onUpdateListaCampanhasBD(Campanha livro, int operacao) {

    }

    @Override
    public void onRefreshListaCategorias(ArrayList<Categoria> listacategorias) {

    }

    @Override
    public void onUpdateListaCategoriasBD(Categoria categoria, int operacao) {

    }

    @Override
    public void onRefreshListaCategoriasChild(ArrayList<CategoriaChild> listacategoriasChild) {

    }

    @Override
    public void onUpdateListaCategoriasChildBD(CategoriaChild categoriaChild, int operacao) {

    }

    @Override
    public void onRefreshListaProdutos(ArrayList<Produto> listaProdutos)
    {
        if (listaProdutos!=null)
        {
            if (lvlistView == null)
            {
                lvlistView = getView().findViewById(R.id.listVIewProducts);
            }
            listaProdutoAdaptador = new ListaProdutoAdaptador(getActivity(), listaProdutos);
            lvlistView.setAdapter(listaProdutoAdaptador);
        }
    }

    @Override
    public void onUpdateListaProdutosBD(Produto produto, int operacao) {

    }

    @Override
    public void onRefreshListaReparacoes(ArrayList<Reparacao> listaReparacoes) {

    }

    @Override
    public void onUpdateListaReparacoesBD(Reparacao reparacao, int operacao) {

    }
}

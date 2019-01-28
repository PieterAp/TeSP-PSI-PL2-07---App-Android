package pt.ipleiria.estg.dei.amsi.fixbyte;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ListView;

        import java.util.ArrayList;

        import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaCategoriaAdaptador;
        import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.FixByteListener;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Categoria;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.CategoriaChild;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.FixByteSingleton;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Produto;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Reparacao;
        import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class CategoriesFragment extends Fragment implements FixByteListener
{
    private ListView lvlistView;
    private Button btnTakeLook;
    private ArrayList<Categoria> listaCategorias;
    private ListaCategoriaAdaptador listaCategoriaAdaptador;

    private CategoriesChildFragment categoriesChildFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_category_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        FixByteSingleton.getInstance(getActivity().getApplicationContext()).setFixByteListener(this);
        FixByteSingleton.getInstance(getActivity().getApplicationContext()).getAllCategoriasAPI(getActivity().getApplicationContext(),FixByteJsonParser.isConnectedInternet(getActivity().getApplicationContext()));

        lvlistView = (ListView) getView().findViewById(R.id.listVIewCategories);

        categoriesChildFragment = new CategoriesChildFragment();

        lvlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Categoria tempCategoria = (Categoria) parent.getItemAtPosition(position);


                Bundle bundle = new Bundle();
                bundle.putLong("categoria_idcategorias",tempCategoria.getIdcategorias());

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                categoriesChildFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_frame, categoriesChildFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    public  void onResume()
    {
        super.onResume();
        FixByteSingleton.getInstance(getActivity().getApplicationContext()).getAllCategoriasAPI(getActivity().getApplicationContext(),FixByteJsonParser.isConnectedInternet(getActivity().getApplicationContext()));
    }

    @Override
    public void onRefreshListaCampanhas(ArrayList<Campanha> listacampanhas) {

    }

    @Override
    public void onUpdateListaCampanhasBD(Campanha livro, int operacao) {

    }

    @Override
    public void onRefreshListaCategorias(ArrayList<Categoria> listaCategorias)
    {
        if (listaCategorias!=null)
        {
            if (lvlistView == null)
            {
                lvlistView = getView().findViewById(R.id.listVIewCategories);
            }
            listaCategoriaAdaptador = new ListaCategoriaAdaptador(getActivity(), listaCategorias);
            lvlistView.setAdapter(listaCategoriaAdaptador);
        }

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
    public void onRefreshListaProdutos(ArrayList<Produto> listaProdutos) {

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

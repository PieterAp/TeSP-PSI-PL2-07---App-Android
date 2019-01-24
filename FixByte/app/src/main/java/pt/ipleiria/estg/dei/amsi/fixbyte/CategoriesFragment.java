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

        import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaCategoriaAdaptador;
        import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.FixByteListener;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Categoria;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.FixByteSingleton;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;
        import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class CategoriesFragment extends Fragment implements FixByteListener
{
    private ListView lvlistView;
    private Button btnTakeLook;
    private ArrayList<Categoria> listaCategorias;
    private ListaCategoriaAdaptador listaCategoriaAdaptador;

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

        lvlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Categoria tempCategoria = (Categoria) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalhesProdutosCampanha.class);
                System.out.println("--> RESPOSTA111: " + position);
                System.out.println("--> NOME DA CAMPANHA CLICADA: " + tempCategoria.getCategoriaNome());

                //intent.putExtra(DetalhesProdutosCampanha.DETALHES_PRODUCTS_SALE, tempCategoria.getIdcategorias());
                //startActivity(intent);
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
    public void onRefreshListaProdutosCampanha(ArrayList<ProdutoCampanha> listaprodutoscampanha) {

    }

    @Override
    public void onUpdateListaProdutosCampanhaBD(ProdutoCampanha produtocampanha, int operacao) {

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
}

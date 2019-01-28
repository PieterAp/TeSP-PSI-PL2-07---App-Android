package pt.ipleiria.estg.dei.amsi.fixbyte;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.preference.PreferenceManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ListView;

        import java.util.ArrayList;

        import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaCompraAdaptador;
        import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaReparacaoAdaptador;
        import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.FixByteListener;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Categoria;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.CategoriaChild;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Compra;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.FixByteSingleton;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Produto;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Reparacao;
        import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class ReparacaoListActivity extends AppCompatActivity implements FixByteListener {

    SharedPreferences sharePref;
    SharedPreferences.Editor editor;

    private ListView lvlistView;
    private ArrayList<Reparacao> listaReparacoes;
    private ListaReparacaoAdaptador listaReparacaoAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparacao_list);

        sharePref = getPreferences(Context.MODE_PRIVATE);
        editor = sharePref.edit();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String Token = preferences.getString("token", "");

        FixByteSingleton.getInstance(getApplicationContext()).setFixByteListener(this);
        FixByteSingleton.getInstance(getApplicationContext()).getAllReparacoesAPI(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()),Token);
    }


    protected void onResume()
    {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String Token = preferences.getString("token", "");
        FixByteSingleton.getInstance(getApplicationContext()).getAllReparacoesAPI(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()),Token);
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
    public void onRefreshListaProdutos(ArrayList<Produto> listaProdutos) {

    }

    @Override
    public void onUpdateListaProdutosBD(Produto produto, int operacao) {

    }

    @Override
    public void onRefreshListaReparacoes(ArrayList<Reparacao> listaReparacoes) {
        this.listaReparacoes = listaReparacoes;
        if (listaReparacoes!=null)
        {
            if (lvlistView == null) {
                lvlistView =  findViewById(R.id.listVIewRepairs);
            }
            listaReparacaoAdaptador = new ListaReparacaoAdaptador(this,listaReparacoes);
            lvlistView.setAdapter(listaReparacaoAdaptador);
        }
    }

    @Override
    public void onUpdateListaReparacoesBD(Reparacao reparacao, int operacao) {

    }
}

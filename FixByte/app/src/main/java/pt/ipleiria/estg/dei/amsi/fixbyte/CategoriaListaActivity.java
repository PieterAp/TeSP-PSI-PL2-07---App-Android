package pt.ipleiria.estg.dei.amsi.fixbyte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaCategoriaAdaptador;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.FixByteListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Categoria;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.FixByteSingleton;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class CategoriaListaActivity extends AppCompatActivity implements FixByteListener
{
    private ListView lvlistView;
    private Button btnTakeLook;
    private ArrayList<Categoria> listaCategorias;
    private ListaCategoriaAdaptador listaCategoriaAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_lista);

        FixByteSingleton.getInstance(getApplicationContext()).setFixByteListener(this);
        FixByteSingleton.getInstance(getApplicationContext()).getAllCategoriasAPI(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()));

        lvlistView = (ListView) findViewById(R.id.listVIewCategories);

        lvlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Categoria tempCategoria = (Categoria) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), DetalhesProdutosCampanha.class);
                System.out.println("--> RESPOSTA111: " + position);
                System.out.println("--> NOME DA CAMPANHA CLICADA: " + tempCategoria.getCategoriaNome());
                Toast.makeText(CategoriaListaActivity.this, "" + tempCategoria.getCategoriaNome(), Toast.LENGTH_SHORT).show();

                //intent.putExtra(DetalhesProdutosCampanha.DETALHES_PRODUCTS_SALE, tempCategoria.getIdcategorias());
                //startActivity(intent);
            }
        });
    }

    protected  void onResume()
    {
        super.onResume();
        FixByteSingleton.getInstance(getApplicationContext()).getAllCategoriasAPI(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()));
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
                lvlistView = findViewById(R.id.listVIewCategories);
            }
            listaCategoriaAdaptador = new ListaCategoriaAdaptador(this, listaCategorias);
            lvlistView.setAdapter(listaCategoriaAdaptador);
        }

    }

    @Override
    public void onUpdateListaCategoriasBD(Categoria categoria, int operacao) {

    }
}

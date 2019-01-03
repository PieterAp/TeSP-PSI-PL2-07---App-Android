package pt.ipleiria.estg.dei.amsi.fixbyte;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaProdutoCampanhaAdaptador;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.ProdutocampanhaListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.FixByteSingleton;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class DetalhesProdutosCampanha extends AppCompatActivity implements ProdutocampanhaListener {
    public static final String DETALHES_PRODUCTS_SALE = "DETALHES_PRODUCTS_SALE";

    SharedPreferences sharePref;
    SharedPreferences.Editor editor;

    Long idCampanha;

    private ListView lvlistView;
    private ArrayList<Campanha> listaCampanhas;
    private ListaProdutoCampanhaAdaptador listacampanhasAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_campanhas);

        sharePref = getPreferences(Context.MODE_PRIVATE);
        editor = sharePref.edit();

        idCampanha = getIntent().getLongExtra(DETALHES_PRODUCTS_SALE,-1);

        //FixByteSingleton.getInstance(getApplicationContext()).setProdutosCampanhaListener(this);
        FixByteSingleton.getInstance(getApplicationContext()).getAllProdutoCampanhaAPI(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()),idCampanha);

        lvlistView = (ListView) findViewById(R.id.listviewCampanhas);


        lvlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Campanha tempCampanha = (Campanha) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), DetalhesProdutosCampanha.class);
                intent.putExtra(DetalhesProdutosCampanha.DETALHES_PRODUCTS_SALE, tempCampanha.getIdCampanha());
                startActivity(intent);
            }
        });

    }


    protected void onResume()
    {
        super.onResume();
        FixByteSingleton.getInstance(getApplicationContext()).getAllCampanhasAPI(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()));
    }

    @Override
    public void onRefreshListaProdutosCampanha(ArrayList<ProdutoCampanha> listaprodutoscampanha)
    {
        if (listaprodutoscampanha!=null)
        {
            if (lvlistView == null) {
                lvlistView =  findViewById(R.id.listviewCampanhas);
            }
            listacampanhasAdaptador = new ListaProdutoCampanhaAdaptador(this,listaprodutoscampanha);
            lvlistView.setAdapter(listacampanhasAdaptador);
        }
    }

    @Override
    public void onUpdateListaProdutosCampanhaBD(ProdutoCampanha produtocampanha, int operacao) {

    }

}

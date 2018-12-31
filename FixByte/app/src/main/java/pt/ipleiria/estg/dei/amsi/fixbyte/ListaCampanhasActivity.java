package pt.ipleiria.estg.dei.amsi.fixbyte;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaCampanhaAdaptador;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.CampanhasListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.FixByteSingleton;
import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;



public class ListaCampanhasActivity extends AppCompatActivity implements CampanhasListener {

    public static final String DADOS_EMAIL = "amsi.dei.estg.ipleiria.pt.EMAIL";

    SharedPreferences sharePref;
    SharedPreferences.Editor editor;

    private ListView lvlistView;
    private ArrayList<Campanha> listaCampanhas;
    private ListaCampanhaAdaptador listacampanhasAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_campanhas);

        sharePref = getPreferences(Context.MODE_PRIVATE);
        editor = sharePref.edit();

        String text = getIntent().getStringExtra(DADOS_EMAIL);

        if (text == null)
        {
            text = sharePref.getString("DADOS_EMAIL",text);
        }
        else
        {
            editor.putString("DADOS_EMAIL",text);
        }

        FixByteSingleton.getInstance(getApplicationContext()).setCampanhasListener(this);
        FixByteSingleton.getInstance(getApplicationContext()).getAllCampanhasAPI(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()));

        lvlistView = (ListView) findViewById(R.id.listviewCampanhas);


        lvlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Campanha tempCacampanha = (Campanha) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), DetalhesProdutosCampanha.class);
                intent.putExtra(DetalhesProdutosCampanha.DETALHES_PRODUCTS_SALE, tempCacampanha.getIdCampanha());
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
    public void onRefreshListaCampanhas(ArrayList<Campanha> listacampanhas)
    {
        if (listacampanhas!=null)
        {
            if (lvlistView == null) {
                lvlistView =  findViewById(R.id.listviewCampanhas);
            }
            listacampanhasAdaptador = new ListaCampanhaAdaptador(this,listacampanhas);
            lvlistView.setAdapter(listacampanhasAdaptador);
        }
    }

    @Override
    public void onUpdateListaCampanhasBD(Campanha campanha, int operacao) {

    }
}

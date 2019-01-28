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

import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaCampanhaAdaptador;
import pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores.ListaCompraAdaptador;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.ComprasListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Compra;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.FixByteSingleton;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class ComprasListActivity extends AppCompatActivity implements ComprasListener {

    SharedPreferences sharePref;
    SharedPreferences.Editor editor;

    private ListView lvlistView;
    private ArrayList<Compra> listaCompras;
    private ListaCompraAdaptador listaCompraAdaptador;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras_list);

        sharePref = getPreferences(Context.MODE_PRIVATE);
        editor = sharePref.edit();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String Token = preferences.getString("token", "");

        FixByteSingleton.getInstance(getApplicationContext()).setComprasListener(this);
        FixByteSingleton.getInstance(getApplicationContext()).APIgetCompras(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()),Token);

        lvlistView = (ListView) findViewById(R.id.listVIewCompras);


        btn = findViewById(R.id.buttonPay);

        btn.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String Token = preferences.getString("token", "");
                FixByteSingleton.getInstance(getApplicationContext()).APIstateCompras(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()),Token);
            }
        });

        lvlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Compra compra = (Compra) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), ComprasItemActivity.class);
                intent.putExtra(ComprasItemActivity.IDPRODUTO, compra.getIdprodutos());
                startActivity(intent);
            }
        });

    }


    protected void onResume()
    {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String Token = preferences.getString("token", "");
        FixByteSingleton.getInstance(getApplicationContext()).APIgetCompras(getApplicationContext(),FixByteJsonParser.isConnectedInternet(getApplicationContext()),Token);
    }

    @Override
    public void onRefreshListaCompra(ArrayList<Compra> compras)
    {
        this.listaCompras = compras;
        if (compras!=null)
        {
            if (lvlistView == null) {
                lvlistView =  findViewById(R.id.listVIewCompras);
            }
            listaCompraAdaptador = new ListaCompraAdaptador(this,compras);
            lvlistView.setAdapter(listaCompraAdaptador);
        }
    }

    @Override
    public void callback(boolean state) {
        finish();
        startActivity(getIntent());
    }

}

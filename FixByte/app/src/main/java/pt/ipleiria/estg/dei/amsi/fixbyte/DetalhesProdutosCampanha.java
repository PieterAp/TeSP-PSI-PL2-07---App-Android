package pt.ipleiria.estg.dei.amsi.fixbyte;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;

public class DetalhesProdutosCampanha extends AppCompatActivity {

    public static final String DETALHES_PRODUCTS_SALE = "DETALHES_PRODUCTS_SALE";
    private EditText txt;
    private EditText editTextSerie;
    private EditText editTextAutor;
    private EditText editTextAno;
/*
    private ProdutoCampanha campanha;
    FloatingActionButton fab;
    long idLivro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_products_sale);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idLivro = getIntent().getLongExtra(DETALHES_PRODUCTS_SALE,-1);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextSerie = findViewById(R.id.editTextSerie);
        editTextAutor = findViewById(R.id.editTextAutor);
        editTextAno = findViewById(R.id.editTextAno);
        imageViewCapa = findViewById(R.id.imageViewCapa);
        fab = findViewById(R.id.fabGuardar);


        if (idLivro == -1)
        {
            setTitle(R.string.AdicionarLivro);tostamistas
            fab.setImageResource(R.drawable.ic_action_save);

        }
        else
        {
            livro = SingletonGestorLivros.getInstance(getApplicationContext()).getLivro(idLivro);
            setTitle("Detalhes" + ((Livro) livro).getTitulo());
            preencherDadosLivro();
            fab.setImageResource(R.drawable.ic_action_delete);
            //btnRemover.setVisible(true);
        }

    }

    private void preencherDadosLivro ()
    {
        editTextTitulo.setText(campanha.get());
        editTextSerie.setText(campanha.getSerie());
        editTextAutor.setText(campanha.getAutor());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalhes_livro, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.itemRemover:
                Toast.makeText(this, "Remover", Toast.LENGTH_SHORT).show();
                SingletonGestorLivros.getInstance(getApplicationContext()).removerLivro(idLivro);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}

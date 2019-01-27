package pt.ipleiria.estg.dei.amsi.fixbyte;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.FixByteSingleton;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Produto;

public class ProductDetailActivity extends AppCompatActivity {
    public static final String DETALHES_PRODUTOS = "DETALHES_PRODUTOS";

    private ImageView imageViewProduto;
    private TextView textViewNome;
    private TextView textViewDescricao;
    private TextView textViewPrecoOriginal;


    private Produto produto;
    FloatingActionButton fab;
    long idProduto;
    private String ImageRoute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        idProduto = getIntent().getLongExtra(DETALHES_PRODUTOS,-1);
        Toast.makeText(this, ""+idProduto, Toast.LENGTH_SHORT).show();

        imageViewProduto = findViewById(R.id.imageViewProduto);
        textViewNome = findViewById(R.id.textViewNome);
        textViewDescricao = findViewById(R.id.textViewDescricao);
        textViewPrecoOriginal = findViewById(R.id.textViewPrecoOriginal);

        produto = FixByteSingleton.getInstance(getApplicationContext()).getProduto(idProduto);
        preencherDadosProduto();
    }

    private void preencherDadosProduto()
    {
        textViewNome.setText(produto.getProdutoNome());
        textViewDescricao.setText(produto.getProdutoDescricao1()+"\n"+produto.getProdutoDescricao2());
        textViewPrecoOriginal.setText(produto.getProdutoPreco().toString()+"€");

        ImageRoute = "C:\\wamp64\\www\\TeSP-PSI-PL2-07-WEB\\frontend\\web\\images\\products";

        Glide.with(getApplicationContext())
                .load(ImageRoute+"\\"+produto.getIdprodutos()+"\\"+produto.getProdutoImagem1())
                .placeholder(R.drawable.logo_fixbyte_drop_shadow)
                .thumbnail(0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewProduto);
    }
}
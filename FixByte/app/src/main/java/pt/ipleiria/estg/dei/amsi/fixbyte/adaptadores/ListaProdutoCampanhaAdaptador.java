package pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.R;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;

public class ListaProdutoCampanhaAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ProdutoCampanha> produtoscampanha;


    public ListaProdutoCampanhaAdaptador(Context context, ArrayList<ProdutoCampanha> produtoscampanha) {
        this.context = context;
        this.produtoscampanha = produtoscampanha;
    }

    @Override
    public int getCount() {
        return produtoscampanha.size();
    }
    @Override
    public Object getItem(int position) {
        return produtoscampanha.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.fragment_sales,null);
        }

        ViewHolderListaproduto viewHolderListaproduto = (ViewHolderListaproduto) convertView.getTag();
        if (viewHolderListaproduto == null)
        {
            viewHolderListaproduto = new ViewHolderListaproduto(convertView);
            convertView.setTag(viewHolderListaproduto);
        }

        viewHolderListaproduto.update(produtoscampanha.get(position));
        return convertView;
    }

    public void refresh(ArrayList<ProdutoCampanha> produtoscampanha)
    {
        this.produtoscampanha=produtoscampanha;
        notifyDataSetChanged();
    }

    private class ViewHolderListaproduto
    {
        private TextView nome;
        private TextView preco;
        private TextView precodpsDesconto;
        private TextView percentagem;



        public ViewHolderListaproduto (View convertView)
        {
            nome = convertView.findViewById(R.id.txtNome);
           // preco = convertView.findViewById(R.id.txtpreco);
            //precodpsDesconto = convertView.findViewById(R.id.txt);
            //percentagem = convertView.findViewById(R.id.txtpercentagem);


        }

        public void update (ProdutoCampanha produtocampanha)
        {
             nome.setText(produtocampanha.getProdutoNome());
             preco.setText(String.valueOf(produtocampanha.getProdutoPreco()));
             precodpsDesconto.setText(produtocampanha.getPrecoDpsDesconto());
             percentagem.setText(produtocampanha.getCampanhaPercentagem());

        }
    }
}

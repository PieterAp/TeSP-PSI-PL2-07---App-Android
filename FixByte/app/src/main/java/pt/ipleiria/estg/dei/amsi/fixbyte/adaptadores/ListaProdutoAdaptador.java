package pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.R;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Produto;

public class ListaProdutoAdaptador extends BaseAdapter
{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Produto> produtos;

    public ListaProdutoAdaptador(Context context, ArrayList<Produto> produtos)
    {
        this.context = context;
        this.produtos = produtos;
    }


    @Override
    public int getCount()
    {
        return produtos.size();
    }

    @Override
    public Object getItem(int position)
    {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position)
    {
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
            convertView = inflater.inflate(R.layout.activity_product_item,null);
        }

        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null)
        {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(produtos.get(position));
        return convertView;
    }

    public void refresh(ArrayList<Produto> produtos){
        this.produtos = produtos;
        notifyDataSetChanged();
    }

    private class ViewHolderLista{
        private TextView produtoNome;
        private TextView produtoPreco;

        public ViewHolderLista (View convertView)
        {
            produtoNome = convertView.findViewById(R.id.textViewNomeProduto);
            produtoPreco = convertView.findViewById(R.id.textViewPrecoProduto);
        }

        public void update (Produto produto)
        {
            produtoNome.setText(produto.getProdutoNome());
            produtoPreco.setText(produto.getProdutoPreco().toString()+"€");
        }
    }
}

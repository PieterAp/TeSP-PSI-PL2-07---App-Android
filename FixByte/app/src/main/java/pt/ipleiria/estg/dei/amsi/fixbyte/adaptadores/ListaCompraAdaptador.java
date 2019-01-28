package pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.R;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Compra;

public class ListaCompraAdaptador extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Compra> compra;

    public ListaCompraAdaptador(Context context, ArrayList<Compra> compra)
    {
        this.context = context;
        this.compra = compra;
    }


    @Override
    public int getCount()
    {
        return compra.size();
    }

    @Override
    public Object getItem(int position)
    {
        return compra.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (inflater == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.activity_compras_item,null);
            view = inflater.inflate(R.layout.activity_compras_list, null);

        }

        ListaCompraAdaptador.ViewHolderLista viewHolderLista = (ListaCompraAdaptador.ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null)
        {
            viewHolderLista = new ListaCompraAdaptador.ViewHolderLista(convertView, view);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(compra.get(position));
        return convertView;
    }

    public void refresh(ArrayList<Compra> compra){
        this.compra = compra;
        notifyDataSetChanged();
    }

    private class ViewHolderLista{
        private TextView produtoNome;
        private TextView produtoPreco;
        private TextView total;
        View view;
        private Double soma;

        public ViewHolderLista (View convertView,View view)
        {
            this.view = view;
            produtoNome = convertView.findViewById(R.id.textViewProdutoNome);
            produtoPreco = convertView.findViewById(R.id.textViewProdutoPreco);


        }

        public void update (Compra compra)
        {
            produtoNome.setText(compra.getProdutoNome());
            produtoPreco.setText(compra.getProduto_preco().toString()+"€");
            //not working
            total = view.findViewById(R.id.textViewTotal);
            total.setText("ola");
        }
    }
}

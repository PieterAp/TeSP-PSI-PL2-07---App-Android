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

        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null)
        {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(produtoscampanha.get(position));
        return convertView;
    }

    public void refresh(ArrayList<ProdutoCampanha> produtoscampanha)
    {
        this.produtoscampanha=produtoscampanha;
        notifyDataSetChanged();
    }

    private class ViewHolderLista
    {
        private TextView nome;
        private TextView dataInicio;
        private TextView dataFim;


        public ViewHolderLista (View convertView)
        {
            nome = convertView.findViewById(R.id.txtNome);
            dataInicio = convertView.findViewById(R.id.txtDataInicio);
            dataFim = convertView.findViewById(R.id.txtDateFim);

        }

        public void update (ProdutoCampanha produtocampanha)
        {
            String stridcampanha = Long.toString(produtocampanha.getCampanha_idCampanha());
            String stridproduto = Long.toString(produtocampanha.getProdutos_idprodutos());

            nome.setText(produtocampanha.getCampanhaPercentagem());
            dataInicio.setText(stridcampanha);
            dataFim.setText(stridproduto);
        }
    }
}

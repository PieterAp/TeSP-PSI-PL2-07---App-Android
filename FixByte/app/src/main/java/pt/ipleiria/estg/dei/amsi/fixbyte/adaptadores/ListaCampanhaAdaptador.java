package pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.R;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;

public class ListaCampanhaAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Campanha> campanhas;


    public ListaCampanhaAdaptador(Context context, ArrayList<Campanha> campanhas) {
        this.context = context;
        this.campanhas = campanhas;
    }

    @Override
    public int getCount() {
        return campanhas.size();
    }
    @Override
    public Object getItem(int position) {
        return campanhas.get(position);
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

        viewHolderLista.update(campanhas.get(position));
        return convertView;
    }

    public void refresh(ArrayList<Campanha> livros)
    {
        this.campanhas=campanhas;
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

        public void update (Campanha sale)
        {
            nome.setText(sale.getCampanhaNome());
            dataInicio.setText(sale.getCampanhaDataInicio());
            dataFim.setText(sale.getCampanhaDataFim());
     }
    }
}

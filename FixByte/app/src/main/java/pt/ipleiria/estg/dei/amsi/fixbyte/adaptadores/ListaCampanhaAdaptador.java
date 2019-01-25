package pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public void refresh(ArrayList<Campanha> campanhas)
    {
        this.campanhas=campanhas;
        notifyDataSetChanged();
    }

    private class ViewHolderLista
    {
        private TextView nome;
        private TextView descricao;
        private TextView dataFim;


        public ViewHolderLista (View convertView)
        {
            nome = convertView.findViewById(R.id.txtNome);
            descricao = convertView.findViewById(R.id.txtDescricao);
            dataFim = convertView.findViewById(R.id.txtDateFim);
        }

        public void update (Campanha campanha)
        {
            nome.setText(campanha.getCampanhaNome());
            descricao.setText(campanha.getCampanhaDescricao());
            dataFim.setText(campanha.getCampanhaDataFimEnglishParse());
        }
    }
}

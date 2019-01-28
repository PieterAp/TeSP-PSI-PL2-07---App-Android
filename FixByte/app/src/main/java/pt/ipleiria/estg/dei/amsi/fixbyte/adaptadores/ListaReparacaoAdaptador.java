package pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;

        import pt.ipleiria.estg.dei.amsi.fixbyte.R;
        import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Reparacao;

public class ListaReparacaoAdaptador extends BaseAdapter
{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Reparacao> reparacoes;

    public ListaReparacaoAdaptador(Context context, ArrayList<Reparacao> reparacoes)
    {
        this.context = context;
        this.reparacoes = reparacoes;
    }


    @Override
    public int getCount()
    {
        return reparacoes.size();
    }

    @Override
    public Object getItem(int position)
    {
        return reparacoes.get(position);
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
            convertView = inflater.inflate(R.layout.activity_reparacao_item,null);
        }

        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null)
        {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(reparacoes.get(position));
        return convertView;
    }

    public void refresh(ArrayList<Reparacao> reparacaos){
        this.reparacoes = reparacoes;
        notifyDataSetChanged();
    }

    private class ViewHolderLista{
        private TextView textViewEstado;
        private TextView textViewDataFim;

        public ViewHolderLista (View convertView)
        {
            textViewEstado = convertView.findViewById(R.id.textViewEstado);
            textViewDataFim = convertView.findViewById(R.id.textViewDataFim);
        }

        public void update (Reparacao reparacao)
        {
            textViewEstado.setText(reparacao.getReparacaoEstado());
            textViewDataFim.setText(reparacao.getReparacaoDataConcluido());
        }
    }
}

package pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.R;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Categoria;

public class ListaCategoriaAdaptador extends BaseAdapter
{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Categoria> categorias;

    public ListaCategoriaAdaptador(Context context, ArrayList<Categoria> categorias)
    {
        this.context = context;
        this.categorias = categorias;
    }


    @Override
    public int getCount()
    {
        return categorias.size();
    }

    @Override
    public Object getItem(int position)
    {
        return categorias.get(position);
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
            convertView = inflater.inflate(R.layout.activity_categories_item,null);
        }

        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null)
        {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(categorias.get(position));
        return convertView;
    }

    public void refresh(ArrayList<Categoria> categorias)
    {
        this.categorias=categorias;
        notifyDataSetChanged();
    }

    private class ViewHolderLista
    {
        private TextView nome;
        private TextView qntItems;

        public ViewHolderLista (View convertView)
        {
            nome = convertView.findViewById(R.id.textViewNomeCategoria);
            //qntItems = convertView.findViewById(R.id.textViewCategoriaQnt);
        }

        public void update (Categoria categoria)
        {
            nome.setText(categoria.getCategoriaNome());
            //qntItems.setText(categoria.get());
        }
    }
}

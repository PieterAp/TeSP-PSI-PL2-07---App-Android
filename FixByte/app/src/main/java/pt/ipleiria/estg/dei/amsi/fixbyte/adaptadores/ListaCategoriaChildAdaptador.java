package pt.ipleiria.estg.dei.amsi.fixbyte.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.R;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.CategoriaChild;

public class ListaCategoriaChildAdaptador extends BaseAdapter
{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<CategoriaChild> categoriasChild;

    public ListaCategoriaChildAdaptador(Context context, ArrayList<CategoriaChild> categoriasChild)
    {
        this.context = context;
        this.categoriasChild = categoriasChild;
    }


    @Override
    public int getCount()
    {
        return categoriasChild.size();
    }

    @Override
    public Object getItem(int position)
    {
        return categoriasChild.get(position);
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
            convertView = inflater.inflate(R.layout.fragment_category_child_item,null);
        }

        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null)
        {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(categoriasChild.get(position));
        return convertView;
    }

    public void refresh(ArrayList<CategoriaChild> categoriasChild){
        this.categoriasChild = categoriasChild;
        notifyDataSetChanged();
    }

    private class ViewHolderLista{
        private TextView categoriaChildNome;
        private TextView produtoQnt;

        public ViewHolderLista (View convertView)
        {
            categoriaChildNome = convertView.findViewById(R.id.textViewNomeCategoriaChild);
            produtoQnt = convertView.findViewById(R.id.textViewProdutoQnt);
        }

        public void update (CategoriaChild categoriaChild)
        {
            categoriaChildNome.setText(categoriaChild.getChildNome());
            produtoQnt.setText(categoriaChild.getQntProdutos().toString() + " Items");
        }
    }
}

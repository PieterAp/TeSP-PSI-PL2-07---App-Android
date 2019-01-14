package pt.ipleiria.estg.dei.amsi.fixbyte.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Categoria;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.User;

public class FixByteJsonParser {

    public static boolean  isConnectedInternet (Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }

    //region campanhas
    public static ArrayList<Campanha> parserJsonCampanhas (JSONArray response, Context context){

        ArrayList<Campanha> tempListCampanha = new ArrayList<>();

        try{
            for (int i = 0; i< response.length();i++){
                JSONObject campanha = (JSONObject) response.get(i);

                long idCampanha     = campanha.getLong("idCampanha");
                String campanhaNome   = campanha.getString("campanhaNome");
                String campanhaDataInicio    = campanha.getString("campanhaDataInicio");
                String campanhaDescricao    = campanha.getString("campanhaDescricao");
                String campanhaDataFim         = campanha.getString("campanhaDataFim");

                Campanha auxCampanha = new Campanha(idCampanha,campanhaNome,campanhaDataInicio,campanhaDescricao,campanhaDataFim);
                tempListCampanha.add(auxCampanha);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return tempListCampanha;

    }
    //endregion

    //region produtoCampanha
    public static ArrayList<ProdutoCampanha> parserJsonProdutosCampanha (JSONArray response, Context context){

        ArrayList<ProdutoCampanha> tempListProdutoCampanha = new ArrayList<>();

        try{
            for (int i = 0; i< response.length();i++){
                JSONObject produtocampanha = (JSONObject) response.get(i);

                long idprodutos     = produtocampanha.getLong("idprodutos");
                String produtoNome   = produtocampanha.getString("produtoNome");
                String produtoCodigo    = produtocampanha.getString("produtoCodigo");
                String produtoDataCriacao    = produtocampanha.getString("produtoDataCriacao");
                long produtoStock    = produtocampanha.getLong("produtoStock");
                double produtoPreco = produtocampanha.getDouble("produtoPreco");
                String produtoMarca = produtocampanha.getString("produtoMarca");
                String produtoDescricao1 = produtocampanha.getString("produtoDescricao1");
                String produtoDescricao2 = produtocampanha.getString("produtoDescricao2");
                String produtoDescricao3 = produtocampanha.getString("produtoDescricao3");
                String produtoDescricao4 = produtocampanha.getString("produtoDescricao4");
                String produtoDescricao5 = produtocampanha.getString("produtoDescricao5");
                String produtoDescricao6 = produtocampanha.getString("produtoDescricao6");
                String produtoDescricao7 = produtocampanha.getString("produtoDescricao7");
                String produtoDescricao8 = produtocampanha.getString("produtoDescricao8");
                String produtoDescricao9 = produtocampanha.getString("produtoDescricao9");
                String produtoDescricao10 = produtocampanha.getString("produtoDescricao10");
                long categoria_child_id = produtocampanha.getLong("categoria_child_id");
                String produtoImagem1 = produtocampanha.getString("produtoImagem1");
                String produtoImagem2 = produtocampanha.getString("produtoImagem2");
                String produtoImagem3 = produtocampanha.getString("produtoImagem3");
                String produtoImagem4 = produtocampanha.getString("produtoImagem4");
                Integer campanhaPercentagem = produtocampanha.getInt("campanhaPercentagem");
                Integer precoDpsDesconto = produtocampanha.getInt("precoDpsDesconto");


                ProdutoCampanha auxProdutoCampanha = new ProdutoCampanha(idprodutos,produtoNome,
                        produtoCodigo,produtoDataCriacao,produtoStock,produtoPreco,produtoMarca,
                        produtoDescricao1,produtoDescricao2,produtoDescricao3,produtoDescricao4,
                        produtoDescricao5,produtoDescricao6,produtoDescricao7,produtoDescricao8,
                        produtoDescricao9,produtoDescricao10,categoria_child_id,produtoImagem1,
                        produtoImagem2,produtoImagem3,produtoImagem4,campanhaPercentagem,precoDpsDesconto);

                tempListProdutoCampanha.add(auxProdutoCampanha);


            }
        }catch (JSONException e){
            e.printStackTrace();
            System.out.println("--> RESPOSTAERROR: " +  e);

        }
        return tempListProdutoCampanha;
    }
    //endregion

    //region login
    public static User parserJsonLogin (String response, Context context){

        User auxUser = null;
        try
        {
            JSONObject user = new JSONObject(response);

            int idLivro = user.getInt("id");
            String username = user.getString("username");
            String email = user.getString("email");
            String access_token = user.getString("access_token");


            auxUser = new User (idLivro,username,email,access_token);
        }catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxUser;

    }
    //endregion

    //region categorias
   public static ArrayList<Categoria> parserJsonCategorias (JSONArray response, Context context){

       ArrayList<Categoria> tempListCategoria = new ArrayList<>();

       try{
           for (int i = 0; i< response.length();i++){
               JSONObject categoria = (JSONObject) response.get(i);

               long idcategorias     = categoria.getLong("idcategorias");
               String categoriaNome   = categoria.getString("categoriaNome");
               String categoriaDescricao    = categoria.getString("categoriaDescricao");
               Integer categoriaEstado    = categoria.getInt("categoriaEstado");
               Integer qntProdutos    = categoria.getInt("qntProdutos");


               Categoria auxCategoria = new Categoria(idcategorias,categoriaNome,categoriaDescricao,categoriaEstado,qntProdutos);
               tempListCategoria.add(auxCategoria);

           }
       }catch (JSONException e){
           e.printStackTrace();
       }
       return tempListCategoria;

   }
    //endregion

    //region login
    public static Map parserJsonRegisto (String response, Context context){

        try
        {
            if (response.equals("Register successfully")){
                response = ok;
                return ok;
            }
            JSONObject user = new JSONObject(response);

            int idLivro = user.getInt("id");
            String username = user.getString("username");
            String email = user.getString("email");
            String access_token = user.getString("access_token");


            auxUser = new User (idLivro,username,email,access_token);
        }catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return null;

    }
    //endregion
}

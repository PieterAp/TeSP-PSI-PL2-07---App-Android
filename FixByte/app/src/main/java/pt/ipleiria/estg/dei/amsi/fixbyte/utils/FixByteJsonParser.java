package pt.ipleiria.estg.dei.amsi.fixbyte.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

                long idprodutocampanha     = produtocampanha.getLong("idprodutocampanha");
                long produtos_idprodutos   = produtocampanha.getLong("produtos_idprodutos");
                long campanha_idCampanha    = produtocampanha.getLong("campanha_idCampanha");
                Integer CampanhaPercentagem    = produtocampanha.getInt("CampanhaPercentagem");

                ProdutoCampanha auxProdutoCampanha = new ProdutoCampanha(idprodutocampanha,produtos_idprodutos,campanha_idCampanha,CampanhaPercentagem);
                tempListProdutoCampanha.add(auxProdutoCampanha);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return tempListProdutoCampanha;
    }
    //endregion

    //region user
    public static ArrayList<User> parserJsonUser (JSONArray response, Context context){

        ArrayList<User> tempListUser = new ArrayList<>();

        try{
            for (int i = 0; i< response.length();i++){
                JSONObject user = (JSONObject) response.get(i);

                long id     = user.getLong("id");
                String username   = user.getString("username");
                String password    = user.getString("password");
                String email    = user.getString("email");

                User auxUser = new User(id,username,password,email);
                tempListUser.add(auxUser);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return tempListUser;
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

               Categoria auxCategoria = new Categoria(idcategorias,categoriaNome,categoriaDescricao,categoriaEstado);
               tempListCategoria.add(auxCategoria);

           }
       }catch (JSONException e){
           e.printStackTrace();
       }
       return tempListCategoria;

   }
    //endregion
}

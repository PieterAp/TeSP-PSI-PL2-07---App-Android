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
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.CategoriaChild;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Produto;
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

            int id = user.getInt("id");
            String username = user.getString("username");
            String email = user.getString("email");
            String access_token = user.getString("access_token");
            String userNomeProprio = user.getString("userNomeProprio");
            String userApelido = user.getString("userApelido");
            String userDataNasc = user.getString("userDataNasc");
            String userMorada = user.getString("userMorada");

            auxUser = new User (id,username,email,access_token,userNomeProprio,userApelido,userDataNasc,userMorada);
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

    //region categoriasChild
    public static ArrayList<CategoriaChild> parserJsonCategoriasChild (JSONArray response, Context context){

        ArrayList<CategoriaChild> tempListCategoriaChild = new ArrayList<>();

        try{
            for (int i = 0; i< response.length();i++){
                JSONObject categoriaChild = (JSONObject) response.get(i);

                long idchild = categoriaChild.getLong("idchild");
                String childNome = categoriaChild.getString("childNome");
                String childDescricao = categoriaChild.getString("childDescricao");
                long categoria_idcategorias = categoriaChild.getLong("categoria_idcategorias");
                Integer childEstado = categoriaChild.getInt("childEstado");
                Integer qntProdutos = categoriaChild.getInt("qntProdutos");

                CategoriaChild auxCategoriaChild = new CategoriaChild(idchild,childNome,childDescricao,categoria_idcategorias,childEstado);
                tempListCategoriaChild.add(auxCategoriaChild);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return tempListCategoriaChild;

    }
    //endregion

    //region login
    public static JSONObject parserJsonRegisto (String response, Context context){
        try
        {
            if (response.equals("Register successfully")){
                return null;
            }
            return new JSONObject(response);

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //endregion

    //region getAccount
    public static ArrayList<User> parserJsonGetUser (JSONArray response, Context context){

        ArrayList<User> tempListUser = new ArrayList<>();

        try{
            for (int i = 0; i< response.length();i++){
                JSONObject user = (JSONObject) response.get(i);

                long id     = user.getLong("id");
                String username   = user.getString("username");
                String email    = user.getString("email");
                String userNomeProprio    = user.getString("userNomeProprio");
                String token    = user.getString("access_token");
                String userApelido         = user.getString("userApelido");
                String userMorada         = user.getString("userMorada");
                String userDataNasc         = user.getString("userDataNasc");

                User auxUser = new User(id,username,email,token,userNomeProprio,userApelido,userDataNasc,userMorada);
                tempListUser.add(auxUser);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return tempListUser;

    }
    //endregion

    //region produtos
    public static ArrayList<Produto> parserJsonProdutos (JSONArray response, Context context){

        ArrayList<Produto> tempListProduto = new ArrayList<>();

        try{
            for (int i = 0; i< response.length();i++){
                JSONObject produto = (JSONObject) response.get(i);

                long idprodutos = produto.getLong("idprodutos");
                String produtoNome = produto.getString("produtoNome");
                String produtoCodigo = produto.getString("produtoCodigo");
                String produtoDataCriacao    = produto.getString("produtoDataCriacao");
                Integer produtoStock = produto.getInt("produtoStock");
                float produtoPreco = produto.getLong("produtoPreco");
                String produtoMarca = produto.getString("produtoMarca");
                String produtoDescricao1 = produto.getString("produtoDescricao1");
                String produtoDescricao2 = produto.getString("produtoDescricao2");
                String produtoDescricao3 = produto.getString("produtoDescricao3");
                String produtoDescricao4 = produto.getString("produtoDescricao4");
                String produtoDescricao5 = produto.getString("produtoDescricao5");
                String produtoDescricao6 = produto.getString("produtoDescricao6");
                String produtoDescricao7 = produto.getString("produtoDescricao7");
                String produtoDescricao8 = produto.getString("produtoDescricao8");
                String produtoDescricao9 = produto.getString("produtoDescricao9");
                String produtoDescricao10 = produto.getString("produtoDescricao10");
                long categoria_child_id = produto.getLong("categoria_child_id");
                String produtoImagem1 = produto.getString("produtoImagem1");
                String produtoImagem2 = produto.getString("produtoImagem2");
                String produtoImagem3 = produto.getString("produtoImagem3");
                String produtoImagem4 = produto.getString("produtoImagem4");
                Integer produtoEstado    = produto.getInt("produtoEstado");

                Produto auxProduto = new Produto(idprodutos,produtoNome,produtoCodigo,produtoStock,produtoPreco,produtoMarca,categoria_child_id,produtoDescricao1,produtoDescricao2,produtoDescricao3,produtoDescricao4,produtoDescricao5,produtoDescricao6,produtoDescricao7,produtoDescricao8,produtoDescricao9,produtoDescricao10,produtoImagem1,produtoImagem2,produtoImagem3,produtoImagem4,produtoEstado);
                tempListProduto.add(auxProduto);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return tempListProduto;
    }
    //endregion
}

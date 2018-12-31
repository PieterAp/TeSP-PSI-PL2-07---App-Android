package pt.ipleiria.estg.dei.amsi.fixbyte.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;
import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.ProdutoCampanha;

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
                    Long produtos_idprodutos   = produtocampanha.getLong("produtos_idprodutos");
                    Long campanha_idCampanha    = produtocampanha.getLong("campanha_idCampanha");
                    Integer CampanhaPercentagem    = produtocampanha.getInt("CampanhaPercentagem");

                    ProdutoCampanha auxProdutoCampanha = new ProdutoCampanha(idprodutocampanha,produtos_idprodutos,campanha_idCampanha,CampanhaPercentagem);
                    tempListProdutoCampanha.add(auxProdutoCampanha);

                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return tempListProdutoCampanha;

        }

    public static Campanha parserJsonProdutosCampanha (String response, Context context){

        Campanha auxCampanha = null;

        try{
            JSONObject produtocampanha = new JSONObject(response);

            long idprodutocampanha     = produtocampanha.getLong("idprodutocampanha");
            Long produtos_idprodutos   = produtocampanha.getLong("produtos_idprodutos");
            Long campanha_idCampanha    = produtocampanha.getLong("campanha_idCampanha");
            Integer CampanhaPercentagem    = produtocampanha.getInt("CampanhaPercentagem");

            ProdutoCampanha auxProdutoCampanha = new ProdutoCampanha(idprodutocampanha,produtos_idprodutos,campanha_idCampanha,CampanhaPercentagem);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return auxCampanha;

    }
    //endregion
}

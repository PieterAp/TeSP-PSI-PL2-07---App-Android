package pt.ipleiria.estg.dei.amsi.fixbyte.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.Campanha;

public class FixByteJsonParser {
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

        public static Campanha parserJsonCampanhas (String response, Context context){

            Campanha auxCampanha = null;

            try{
                JSONObject campanha = new JSONObject(response);

                int idCampanha     = campanha.getInt("idCampanha");
                String campanhaNome   = campanha.getString("campanhaNome");
                String campanhaDataInicio    = campanha.getString("campanhaDataInicio");
                String campanhaDescricao    = campanha.getString("campanhaDescricao");
                String campanhaDataFim         = campanha.getString("campanhaDataFim");

                auxCampanha = new Campanha(idCampanha,campanhaNome,campanhaDescricao,campanhaDataInicio,campanhaDataFim);

            }catch (JSONException e){
                e.printStackTrace();
            }
            return auxCampanha;

        }

        public static boolean  isConnectedInternet (Context context){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

        }
}

package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.Context;

import org.json.JSONArray;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.CampanhasListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class FixByteSingleton implements CampanhasListener{
    private static FixByteSingleton INSTANCE = null;

    private ArrayList<Campanha> campanhas;
    private CampanhaBDHelper campanhaBDHelper = null;

    private String mUrlAPICampanhas = "http://192.168.1.69:8888/v1/campanhas";

    private String mUrlAPILogin = "http://amsi.dei.estg.ipleiria.pt/api/auth/login";

    private static RequestQueue volleyQueue;

    private CampanhasListener campanhasListener;


    public static synchronized FixByteSingleton getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = new FixByteSingleton(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }

    private FixByteSingleton(Context context){
        campanhas = new ArrayList<>();
        campanhaBDHelper = new CampanhaBDHelper(context);
    }

    //region Campanha

    public ArrayList<Campanha> getCampanhas()
    {
        return new ArrayList<>(campanhas);
    }

    public Campanha getCampanha (long idCampanha)
    {
        for (Campanha campanha : campanhas)
        {
            if (campanha.getIdCampanha() == idCampanha)
            {
                return campanha;
            }
        }
        return null;
    }

    public void adicionarCampanhaBD(Campanha campanha)
    {

        campanhaBDHelper.adicionarCampanhaBD(campanha);
    }

    public void adicionarCampanhasBD(ArrayList<Campanha> listaCampanhas)
    {

        campanhaBDHelper.removeAllCampanhas();
        for (Campanha campanha : listaCampanhas){
            adicionarCampanhaBD(campanha);
        }
    }
    public void getAllCampanhasAPI (final Context context, boolean isConnected){

        if (!isConnected){
            campanhas = campanhaBDHelper.getAllCampanhasBD();
            //mais coisas

        }else{

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPICampanhas, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);


                    campanhas = FixByteJsonParser.parserJsonCampanhas(response,context);
                    adicionarCampanhasBD(campanhas);

                    if(campanhasListener != null)
                    {
                        campanhasListener.onRefreshListaCampanhas(campanhas);

                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    System.out.println("ERROR: " + error);

                }

            });
            volleyQueue.add(req);
        }

    }
    public void removerCampanha (long idCampanha)
    {
        Campanha livroaux = getCampanha(idCampanha);
        if (livroaux!=null)
        {
            if (campanhaBDHelper.removerLivroBD((livroaux.getIdCampanha())))
            {
                campanhas.remove(livroaux);
            }
        }
    }
    public void editarCampanha (Campanha campanha)
    {
        if (!campanhas.contains(campanha))
        {
            return;
        }

        Campanha campanhaux = getCampanha(campanha.getIdCampanha());
        campanhaux.setCampanhaNome(campanha.getCampanhaNome());
        campanhaux.setCampanhaDataFim(campanha.getCampanhaDataFim());
        campanhaux.setCampanhaDataInicio(campanha.getCampanhaDataInicio());
        campanhaux.setCampanhaDescricao(campanha.getCampanhaDescricao());

        if (campanhaBDHelper.editarLivroBD(campanha))
        {
            System.out.println("CAMPANHAS ADICIONADAS");
        }
    }

    public void setCampanhasListener (CampanhasListener campanhasListener)
    {
        this.campanhasListener = campanhasListener;
    }

    @Override
    public void onRefreshListaCampanhas(ArrayList<Campanha> listacampanhas)
    {

    }

    @Override
    public void onUpdateListaLivrosBD(Campanha campanha, int operacao)
    {
        switch (operacao){
            case 1: adicionarCampanhaBD(campanha);
                break;
            case 2: editarCampanha(campanha);
                break;
            case 3: removerCampanha(campanha.getIdCampanha());
                break;
        }
    }
    //endregion
}

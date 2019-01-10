package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.FixByteListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.LoginListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.UserListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class FixByteSingleton implements FixByteListener, LoginListener {
    private static FixByteSingleton INSTANCE = null;

    private ArrayList<Campanha> campanhas;
    private ArrayList<ProdutoCampanha> produtoscampanha;
    private User user;


    private CampanhaBDHelper campanhaBDHelper = null;
    private UserBDHelper userBDHelper = null;
    private UserdataBDHelper userdataBDHelper = null;


    //private String mUrlAPICampanhas = "http://192.168.235.41:8888/v1/campanhas";
    private String mUrlAPIProdutosCampanhas = "http://192.168.235.41:8888/v1/campanhas/";
    private String mUrlAPICampanhas = "http://192.168.235.41:8888/v1/campanhas";
    private String mUrlAPIUser = "http://192.168.235.41:8888/v1/users";
    private String mUrlAPIUserData = "http://192.168.235.41:8888/v1/users";
    private String APILogin = "http://192.168.235.41:8888/v1/users/login";

    private static RequestQueue volleyQueue;

    private FixByteListener fixByteListener;
    private LoginListener loginListener;



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

        produtoscampanha = new ArrayList<>();
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
        for (Campanha campanha : listaCampanhas){
            adicionarCampanhaBD(campanha);
        }
    }
    public void getAllCampanhasAPI (final Context context, boolean isConnected){

        if (!isConnected){
            campanhas = campanhaBDHelper.getAllCampanhasBD();
            if (!campanhas.isEmpty()){
                if(fixByteListener != null)
                {
                    fixByteListener.onRefreshListaCampanhas(campanhas);
                }
            }


        }else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPICampanhas, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);
                    campanhas = FixByteJsonParser.parserJsonCampanhas(response,context);

                    if(fixByteListener != null)
                    {
                        campanhaBDHelper.removeAllCampanhas();
                        adicionarCampanhasBD(campanhas);
                        fixByteListener.onRefreshListaCampanhas(campanhas);
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
        Campanha campanhaaux = getCampanha(idCampanha);
        if (campanhaaux!=null)
        {
            if (campanhaBDHelper.removerCampanhaBD((campanhaaux.getIdCampanha())))
            {
                campanhas.remove(campanhaaux);
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

        if (campanhaBDHelper.editarCampanhaBD(campanha))
        {
            System.out.println("CAMPANHAS ADICIONADAS");
        }
    }

    public void setFixByteListener(FixByteListener fixByteListener)
    {
        this.fixByteListener = fixByteListener;
    }

    @Override
    public void onRefreshListaCampanhas(ArrayList<Campanha> listacampanhas)
    {

    }

    @Override
    public void onUpdateListaCampanhasBD(Campanha campanha, int operacao)
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

    //region produtoscampanha

    public ArrayList<ProdutoCampanha> getProdutoscampanha()
    {
        return new ArrayList<>(produtoscampanha);
    }

    public ProdutoCampanha getProdutoCampanha (long idprodutocampanha)
    {
        for (ProdutoCampanha produtocampanha : produtoscampanha)
        {
            if (produtocampanha.getIdprodutos() == idprodutocampanha)
            {
                return produtocampanha;
            }
        }
        return null;
    }




    public void getAllProdutoCampanhaAPI (final Context context, boolean isConnected, Long idCampanha)
    {
        Toast.makeText(context, "isConnected: " + isConnected, Toast.LENGTH_SHORT).show();
        String mUrlAPIProdutosCampanhas1;
        mUrlAPIProdutosCampanhas1 = null;
        mUrlAPIProdutosCampanhas1 = mUrlAPIProdutosCampanhas;
        mUrlAPIProdutosCampanhas1 += idCampanha;
        mUrlAPIProdutosCampanhas1 += "/produtos";
        if(!isConnected)
        {

        }else{


            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIProdutosCampanhas1, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTARESPONSE: " + response);

                    produtoscampanha = FixByteJsonParser.parserJsonProdutosCampanha(response,context);
                    fixByteListener.onRefreshListaProdutosCampanha(produtoscampanha);

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



    public void setProdutosCampanhaListener (FixByteListener fixByteListener )
    {
        this.fixByteListener = fixByteListener;
    }

    @Override
    public void onRefreshListaProdutosCampanha(ArrayList<ProdutoCampanha> listaprodutoscampanha)
    {

    }

    @Override
    public void onUpdateListaProdutosCampanhaBD(ProdutoCampanha produtocampanha, int operacao)
    {
    }
    //endregion

    //region Login

    public void APILogin(final Context context, final String username, final String password){


        StringRequest req = new StringRequest
                (Request.Method.POST, APILogin, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("--> RESPOSTA ADD POST : "+response);


                        if (response.equals("false")){
                            user = null;
                        }else{
                            user = FixByteJsonParser.parserJsonLogin(response,context);

                            if (user != null){
                                if(loginListener != null)
                                {
                                    loginListener.onUpdateLogin(true, user.getToken());
                                }
                            }else{
                                if(loginListener != null) {
                                    loginListener.onUpdateLogin(false, null);
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d( "Errorr ADD: " + error.getMessage());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        volleyQueue.add(req);
    }
    //endregion

    public void setUpdateLogin(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void onUpdateLogin(boolean key, String token) {
    }

}

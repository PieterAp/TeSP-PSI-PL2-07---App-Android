package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.FixByteListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.UserListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class FixByteSingleton implements FixByteListener, UserListener {
    private static FixByteSingleton INSTANCE = null;

    private ArrayList<Campanha> campanhas;
    private ArrayList<ProdutoCampanha> produtoscampanha;
    private ArrayList<User> users;
    private ArrayList<Userdata> usersdata;



    private ProdutoCampanhaBDHelper produtocampanhaBDHelper = null;
    private CampanhaBDHelper campanhaBDHelper = null;
    private UserBDHelper userBDHelper = null;
    private UserdataBDHelper userdataBDHelper = null;


    //private String mUrlAPICampanhas = "http://192.168.1.69:8888/v1/campanhas";
    private String mUrlAPIProdutosCampanhas = "http://192.168.1.69:8888/v1/campanhas/";
    private String mUrlAPICampanhas = "http://192.168.1.69:8888/v1/campanhas";
    private String mUrlAPIUser = "http://192.168.1.69:8888/v1/users";
    private String mUrlAPIUserData = "http://192.168.1.69:8888/v1/users";

    private static RequestQueue volleyQueue;

    private FixByteListener fixByteListener;



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
        produtocampanhaBDHelper = new ProdutoCampanhaBDHelper(context);
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
            System.out.println("CAMPANHAS1: " + campanhas);

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
            if (produtocampanha.getIdprodutocampanha() == idprodutocampanha)
            {
                return produtocampanha;
            }
        }
        return null;
    }

    public void adicionarProdutoCampanhaBD(ProdutoCampanha produtocampanha)
    {
        produtocampanhaBDHelper.adicionarProdutoCampanhaBD(produtocampanha);
    }

    public void adicionarProdutosCampanhaBD(ArrayList<ProdutoCampanha> listaProdutoscampanha)
    {
        produtocampanhaBDHelper.removeAllProdutosCampanha();
        for (ProdutoCampanha produtocampanha : produtoscampanha)
        {
            adicionarProdutoCampanhaBD(produtocampanha);
        }
    }


    public void getAllProdutoCampanhaAPI (final Context context, boolean isConnected, Long idCampanha)
    {
        Toast.makeText(context, "isConnected: " + isConnected, Toast.LENGTH_SHORT).show();

        if(!isConnected)
        {
            produtoscampanha = produtocampanhaBDHelper.getAllProdutoCampanhasBD(idCampanha);

            if (!produtoscampanha.isEmpty()){
                if(fixByteListener != null)
                {
                    fixByteListener.onRefreshListaProdutosCampanha(produtoscampanha);
                }
            }
        }else{
            mUrlAPIProdutosCampanhas += idCampanha;
            mUrlAPIProdutosCampanhas += "/produtos";
            System.out.println("--> RESPOSTA: " + mUrlAPIProdutosCampanhas);

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIProdutosCampanhas, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);

                    produtoscampanha = FixByteJsonParser.parserJsonProdutosCampanha(response,context);
                    adicionarProdutosCampanhaBD(produtoscampanha);

                    if(fixByteListener != null)
                    {
                        produtocampanhaBDHelper.removeAllProdutosCampanha();
                        adicionarProdutosCampanhaBD(produtoscampanha);
                        fixByteListener.onRefreshListaProdutosCampanha(produtoscampanha);
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
    public void removerProdutocampanha (long idprodutocampanha)
    {
        ProdutoCampanha produticampanhaaux = getProdutoCampanha(idprodutocampanha);
        if (produticampanhaaux!=null)
        {
            if (produtocampanhaBDHelper.removerProdutoCampanhaBD((produticampanhaaux.getIdprodutocampanha())))
            {
                produtoscampanha.remove(produticampanhaaux);
            }
        }
    }

    public void editarProdutoCampanha (ProdutoCampanha produtoCampanha)
    {
        if (!produtoscampanha.contains(produtoCampanha))
        {
            return;
        }

        ProdutoCampanha produtocampanhaux = getProdutoCampanha(produtoCampanha.getIdprodutocampanha());
        produtocampanhaux.setIdprodutocampanha(produtoCampanha.getCampanha_idCampanha());
        produtocampanhaux.setProdutos_idprodutos(produtoCampanha.getProdutos_idprodutos());
        produtocampanhaux.setCampanha_idCampanha(produtoCampanha.getCampanha_idCampanha());


        if (produtocampanhaBDHelper.editarProdutoCampanhaBD(produtoCampanha))
        {
            System.out.println("PRODUTOSCAMPANHAS ADICIONADAS");
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
        switch (operacao){
            case 1: adicionarProdutoCampanhaBD(produtocampanha);
                break;
            case 2: editarProdutoCampanha(produtocampanha);
                break;
            case 3: removerCampanha(produtocampanha.getIdprodutocampanha());
                break;
        }
    }
    //endregion

    //region user
    public ArrayList<User> getUser()
    {
        return new ArrayList<>(users);
    }

    public User getUser (long id)
    {
        for (User user : users)
        {
            if (user.getId() == id)
            {
                return user;
            }
        }
        return null;
    }

    public void adicionarUserBD(User user)
    {
        userBDHelper.adicionarUserBD(user);
    }

    public void adicionarUserBD(ArrayList<User> listaUser)
    {
        userBDHelper.removeAllUsers();
        for (User user : users)
        {
            adicionarUserBD(user);
        }
    }


    public void getAllUserAPI (final Context context, boolean isConnected)
    {
        Toast.makeText(context, "isConnected: " + isConnected, Toast.LENGTH_SHORT).show();

        if(!isConnected)
        {
            users = userBDHelper.getAllUsersBD();

            if (!users.isEmpty()){
                if(fixByteListener != null)
                {
                    //fixByteListener.onRefreshListaUsers(users);
                }
            }
        }else{
            mUrlAPIUser += 1;
            mUrlAPIUser += "/produtos";
            System.out.println("--> RESPOSTA: " + mUrlAPIUser);

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIUser, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);

                    users = FixByteJsonParser.parserJsonUser(response,context);
                    adicionarUserBD(users);

                    if(fixByteListener != null)
                    {
                        produtocampanhaBDHelper.removeAllProdutosCampanha();
                        adicionarProdutosCampanhaBD(produtoscampanha);
                        fixByteListener.onRefreshListaProdutosCampanha(produtoscampanha);
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
    public void removerUser (long id)
    {
        User useraux = getUser(id);
        if (useraux!=null)
        {
            if (userBDHelper.removerUserBD((useraux.getId())))
            {
                users.remove(useraux);
            }
        }
    }

    public void editarUser (User user)
    {
        if (!users.contains(user))
        {
            return;
        }

        User useraux = getUser(user.getId());
        useraux.setUsername(user.getUsername());
        useraux.setPassword_hash(user.getPassword_hash());
        useraux.setEmail(user.getEmail());


        if (userBDHelper.editarUserBD(useraux))
        {
            System.out.println("USER ADICIONADO");
        }
    }
    public void setUserListener (FixByteListener fixByteListener )
    {
        this.fixByteListener = fixByteListener;
    }

    @Override
    public void onRefreshListaUsers(ArrayList<User> listausers)
    {

    }

    @Override
    public void onUpdateListaUsersBD(User user, int operacao)
    {
        switch (operacao){
            case 1: adicionarUserBD(user);
                break;
            case 2: editarUser(user);
                break;
            case 3: removerUser(user.getId());
                break;
        }
    }
    //endregion

}

package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

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

import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.ComprasListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.FixByteListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.LoginListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.RegisterListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.listeners.UserListener;
import pt.ipleiria.estg.dei.amsi.fixbyte.utils.FixByteJsonParser;

public class FixByteSingleton implements FixByteListener, LoginListener, RegisterListener, UserListener, ComprasListener {
    private static FixByteSingleton INSTANCE = null;

    private ArrayList<Campanha> campanhas;
    private ArrayList<User> userdata;
    private ArrayList<ProdutoCampanha> produtoscampanha;
    private ArrayList<Categoria> categorias;
    private ArrayList<CategoriaChild> categoriasChild;
    private ArrayList<Produto> produtos;
    private ArrayList<Compra> compras;

    private User user;
    private JSONObject registo;

    private FixByteBDHelper bdhelper = null;

    public String IPAdress = "192.168.1.84";
    private String Port = "8888";

    private String mUrlAPIProdutosCampanhas = "http://"+IPAdress+":"+Port+"/v1/campanhas/";
    private String mUrlAPICampanhas = "http://"+IPAdress+":"+Port+"/v1/campanhas";
    private String mUrlAPICategorias = "http://"+IPAdress+":"+Port+"/v1/categorias";
    private String mUrlAPICategoriasChild = "http://"+IPAdress+":"+Port+"/v1/categoriaschild";
    private String APILogin = "http://"+IPAdress+":"+Port+"/v1/users/login";
    private String APIRegisto = "http://"+IPAdress+":"+Port+"/v1/users/registo";
    private String APIgetAccount = "http://"+IPAdress+":"+Port+"/v1/users/account?accesstoken=";
    private String APIsetAccount = "http://"+IPAdress+":"+Port+"/v1/users/edit";
    private String APIgetCompras = "http://"+IPAdress+":"+Port+"/v1/compras/getcompras?accesstoken=";
    private String APIsetCompras = "http://"+IPAdress+":"+Port+"/v1/compras/setcompras";
    private String mUrlAPIProdutos = "http://"+IPAdress+":"+Port+"/v1/produtos";

    private static RequestQueue volleyQueue;

    private FixByteListener fixByteListener;
    private LoginListener loginListener;
    private RegisterListener registerListener;
    private UserListener userListener;
    private ComprasListener comprasListener;

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
        bdhelper = new FixByteBDHelper(context);

        produtoscampanha = new ArrayList<>();
        compras = new ArrayList<>();
        categorias = new ArrayList<>();
        categoriasChild = new ArrayList<>();
        produtos = new ArrayList<>();
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

        bdhelper.adicionarCampanhaBD(campanha);
    }

    public void adicionarCampanhasBD(ArrayList<Campanha> listaCampanhas)
    {
        for (Campanha campanha : listaCampanhas){
            adicionarCampanhaBD(campanha);
        }
    }
    public void getAllCampanhasAPI (final Context context, boolean isConnected){

        if (!isConnected){
            campanhas = bdhelper.getAllCampanhasBD();
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
                        bdhelper.removeAllCampanhas();
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
            if (bdhelper.removerCampanhaBD((campanhaaux.getIdCampanha())))
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

        if (bdhelper.editarCampanhaBD(campanha))
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

        if(isConnected)
        {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIProdutosCampanhas1, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);
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

    //region login
    public void APILogin(final Context context, final String username, final String password){

        StringRequest req = new StringRequest
                (Request.Method.POST, APILogin, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("false")){
                            user = null;
                        }else{
                            user = FixByteJsonParser.parserJsonLogin(response,context);

                            if (user != null){
                                if(loginListener != null)
                                {
                                    loginListener.onUpdateLogin(true, user.getToken());
                                    bdhelper.removeAllUsers();
                                    adicionarUserBD(user);
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

    public void setUpdateLogin(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void onUpdateLogin(boolean key, String token) {
    }
    //endregion

    //region registo
    public void APIRegisto(final Context context, final String firstname, final String lastname, final String email, final long nif, final String date, final String address, final String username, final String password){
        StringRequest req = new StringRequest
                (Request.Method.POST, APIRegisto, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("false")){
                            registo = null;
                        }else{
                            registo = FixByteJsonParser.parserJsonRegisto(response,context);
                            if (registo != null){
                                if(registerListener != null)
                                {
                                    registerListener.onUpdateRegisto(registo);
                                }
                            }else{
                                if(registerListener != null) {
                                    registerListener.onUpdateRegisto(registo);
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
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("nif", Long.toString(nif));
                params.put("address", address);
                params.put("birthday", date);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void setUpdateRegisto(RegisterListener registerListener) {
        this.registerListener = registerListener;
    }

    @Override
    public void onUpdateRegisto(JSONObject error) {
    }
    //endregion

    //region categoria
    public ArrayList<Categoria> getCategorias()
    {
        return new ArrayList<>(categorias);
    }

    public Categoria getCategoria (long idcategorias)
    {
        for (Categoria categoria : categorias)
        {
            if (categoria.getIdcategorias() == idcategorias)
            {
                return categoria;
            }
        }
        return null;
    }

    public void adicionarCategoriaBD(Categoria categoria)
    {

        bdhelper.adicionarCategoriaBD(categoria);
    }

    public void adicionarCategoriasBD(ArrayList<Categoria> listaCategorias)
    {
        for (Categoria categoria : listaCategorias){
            adicionarCategoriaBD(categoria);
        }
    }

    public void getAllCategoriasAPI (final Context context, boolean isConnected){

        if (!isConnected){
            categorias = bdhelper.getAllCategoriasBD();
            if (!categorias.isEmpty()){
                if(fixByteListener != null)
                {
                    fixByteListener.onRefreshListaCategorias(categorias);
                }
            }
        }else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPICategorias, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);
                    categorias = FixByteJsonParser.parserJsonCategorias(response,context);

                    if(fixByteListener != null)
                    {
                        bdhelper.removeAllCategorias();
                        adicionarCategoriasBD(categorias);
                        fixByteListener.onRefreshListaCategorias(categorias);
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

    @Override
    public void onRefreshListaCategorias(ArrayList<Categoria> listacategorias)
    {

    }

    @Override
    public void onUpdateListaCategoriasBD(Categoria categoria, int operacao)
    {

    }

    //endregion

    //region categoriaChild
    public ArrayList<CategoriaChild> getCategoriasChild()
    {
        return new ArrayList<>(categoriasChild);
    }

    public CategoriaChild getCategoriaChild (long idchild)
    {
        for (CategoriaChild categoriaChild : categoriasChild)
        {
            if (categoriaChild.getIdchild() == idchild)
            {
                return categoriaChild;
            }
        }
        return null;
    }

    public CategoriaChild getCategoriaChildByMainID (long categoria_idcategorias)
    {
        for (CategoriaChild categoriaChild : categoriasChild)
        {
            if (categoriaChild.getCategoria_idcategorias() == categoria_idcategorias)
            {
                return categoriaChild;
            }
        }
        return null;
    }

    public void adicionarCategoriaChildBD(CategoriaChild categoriaChild)
    {
        bdhelper.adicionarCategoriaChildBD(categoriaChild);
    }

    public void adicionarCategoriasChildBD(ArrayList<CategoriaChild> listaCategoriasChild)
    {
        for (CategoriaChild categoriaChild : listaCategoriasChild)
        {
            adicionarCategoriaChildBD(categoriaChild);
        }
    }

    public void getAllCategoriasChildAPI (final Context context, boolean isConnected){

        if (!isConnected){
            categoriasChild = bdhelper.getAllCategoriasChildBD();
            if (!categoriasChild.isEmpty()){
                if(fixByteListener != null)
                {
                    fixByteListener.onRefreshListaCategoriasChild(categoriasChild);
                }
            }
        }else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPICategoriasChild, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);
                    categoriasChild = FixByteJsonParser.parserJsonCategoriasChild(response,context);

                    if(fixByteListener != null)
                    {
                        bdhelper.removeAllCategoriasChild();
                        adicionarCategoriasChildBD(categoriasChild);
                        fixByteListener.onRefreshListaCategoriasChild(categoriasChild);
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

    @Override
    public void onRefreshListaCategoriasChild(ArrayList<CategoriaChild> listacategoriasChild)
    {

    }

    @Override
    public void onUpdateListaCategoriasChildBD(CategoriaChild categoriaChild, int operacao)
    {

    }

    //endregion

    //region compras
    public void APIsetCompras(final Context context, final String accesstoken, final long id){

        StringRequest req = new StringRequest
                (Request.Method.PUT, APIsetCompras, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("false")){
                            compras = null;
                        }else{
                            Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show();
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
                params.put("accesstoken", accesstoken);
                params.put("idproduto", ""+id);
                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void APIgetCompras (final Context context, boolean isConnected, String accesstoken){
        if (isConnected){
            String APIgetCompras1 = APIgetCompras;
            APIgetCompras1 += accesstoken;

            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, APIgetCompras1, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);
                    compras = FixByteJsonParser.parserJsongetCompras(response,context);
                    if(fixByteListener != null)
                    {
                        comprasListener.onRefreshListaCompra(compras);

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
    public void setComprasListener(ComprasListener comprasListener)
    {
        this.comprasListener = comprasListener;
    }

    @Override
    public void onRefreshListaCompra(ArrayList<Compra> compra) {

    }
    //endregion

    //region account set and get
    public ArrayList<User> getUsers()
    {
        return new ArrayList<>(userdata);
    }

    public User getUser (long id)
    {
        for (User user : userdata)
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

        bdhelper.adicionarUserBD(user);
    }

    public void adicionarUsersBD(ArrayList<User> listaUsers)
    {
        for (User user : listaUsers){
            adicionarUserBD(user);
        }
    }

    public void APIEditAccount(final Context context, boolean isConnected, final String accesstoken,final String firstname, final String lastname, final String date, final String address,final String password){

        if (!isConnected){
            Toast.makeText(context, "You must be connected to the internet", Toast.LENGTH_SHORT).show();

        }else {
            StringRequest req = new StringRequest
                    (Request.Method.PUT, APIsetAccount, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("--> RESPOSTA321: " + response);

                            if (response.equals("\"Alteracao com sucesso\"")){
                                Toast.makeText(context, "Changed successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                    params.put("accesstoken", accesstoken);
                    params.put("firstname", firstname);
                    params.put("lastname", lastname);
                    params.put("date", date);
                    params.put("address", address);
                    params.put("password", password);
                    return params;
                }
            };
            volleyQueue.add(req);
        }

    }

    public void APIgetAccount (final Context context, boolean isConnected, String token){
        String APIgetAccount1 = APIgetAccount;
        APIgetAccount1 += token;
        System.out.println("--> RESPOSTA: " + token);

        if (!isConnected){
            userdata = bdhelper.getAllUsersBD();
            if (!userdata.isEmpty()){
                if(userListener != null)
                {
                    userListener.onRefreshListaUser(userdata);
                }
            }
        }else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, APIgetAccount1, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    userdata = FixByteJsonParser.parserJsonGetUser(response,context);
                    userListener.onRefreshListaUser(userdata);

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

    public void removerUser (long idUser)
    {
        User useraux = getUser(idUser);
        if (useraux!=null)
        {
            if (bdhelper.removerUserBD((useraux.getId())))
            {
                userdata.remove(useraux);
            }
        }
    }
    public void setUserListener(UserListener userListener)
    {
        this.userListener = userListener;
    }

    @Override
    public void onRefreshListaUser(ArrayList<User> userdata) {

    }
    //endregion

    //region produto
    public ArrayList<Produto> getProdutos()
    {
        return new ArrayList<>(produtos);
    }

    public Produto getProduto (long idprodutos)
    {
        for (Produto produto : produtos)
        {
            if (produto.getIdprodutos() == idprodutos)
            {
                return produto;
            }
        }
        return null;
    }

    public void adicionarProdutoBD(Produto produto)
    {
        bdhelper.adicionarProdutoBD(produto);
    }

    public void adicionarProdutosBD(ArrayList<Produto> listaProdutos)
    {
        for (Produto produto : listaProdutos){
            adicionarProdutoBD(produto);
        }
    }

    public void getAllProdutosAPI (final Context context, boolean isConnected){

        if (!isConnected){
            produtos = bdhelper.getAllProdutosBD();
            if (!produtos.isEmpty()){
                if(fixByteListener != null)
                {
                    fixByteListener.onRefreshListaProdutos(produtos);
                }
            }
        }else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIProdutos, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("--> RESPOSTA: " + response);
                    produtos = FixByteJsonParser.parserJsonProdutos(response,context);

                    if(fixByteListener != null)
                    {
                        bdhelper.removeAllProdutos();
                        adicionarProdutosBD(produtos);
                        fixByteListener.onRefreshListaProdutos(produtos);
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

    @Override
    public void onRefreshListaProdutos(ArrayList<Produto> listaprodutos)
    {

    }

    @Override
    public void onUpdateListaProdutosBD(Produto produto, int operacao)
    {

    }
    //endregion


}

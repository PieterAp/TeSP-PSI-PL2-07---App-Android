package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FixByteBDHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tesp-psi-pl2-07-web";

    private static  final int DB_VERSION =1;
    private static final String TABLE_NAME = "campanha";
    private static final String CAMPANHAID = "idCampanha";
    private static final String CAMPANHANOME = "campanhaNome";
    private static final String CAMPANHADATAINICIO = "campanhaDataInicio";
    private static final String CAMPANHADATAFIM = "campanhaDataFim";
    private static final String CAMPANHADESCRICAO = "campanhaDescricao";


    private static  final int DB_VERSION_USER = 1;
    private static final String TABLE_NAME_USER = "user";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String ACCESSTOKEN = "accesstoken";
    private static final String NOMEPROPRIO = "userNomeProprio";
    private static final String APELIDO = "userApelido";
    private static final String MORADA = "userMorada";
    private static final String DATANASC = "userDataNasc";


    private static final String TABLE_NAME_CATEGORIA = "categoria";
    private static final String ID_CATEGORIAS = "idcategorias";
    private static final String CATEGORIANOME = "categoriaNome";
    private static final String CATEGORIADESCRICAO = "categoriaDescricao";
    private static final String CATEGORIAESTADO = "categoriaEstado";
    private static final String QNTPRODUTOS = "qntProdutos";


    private static final String TABLE_NAME_CATEGORIA_CHILD = "categoria_child";
    private static final String IDCHILD = "idchild";
    private static final String CHILDNOME = "childNome";
    private static final String CHILDDESCRICAO = "childDescricao";
    private static final String CATEGORIA_IDCATEGORIAS = "categoria_idcategorias";
    private static final String CHILDESTADO = "childEstado";


    private static final String TABLE_NAME_PRODUTO = "produto";
    private static final String IDPRODUTOS = "idprodutos";
    private static final String PRODUTONOME = "produtoNome";
    private static final String PRODUTOCODIGO = "produtoCodigo";
    private static final String PRODUTODATACRIACAO = "produtoDataCriacao";
    private static final String PRODUTOSTOCK = "produtoStock";
    private static final String PRODUTOPRECO = "produtoPreco";
    private static final String PRODUTOMARCA = "produtoMarca";
    private static final String CATEGORIA_CHILDID = "categoria_child_id";
    private static final String PRODUTODESCRICAO1 = "produtoDescricao1";
    private static final String PRODUTODESCRICAO2 = "produtoDescricao2";
    private static final String PRODUTODESCRICAO3 = "produtoDescricao3";
    private static final String PRODUTODESCRICAO4 = "produtoDescricao4";
    private static final String PRODUTODESCRICAO5 = "produtoDescricao5";
    private static final String PRODUTODESCRICAO6 = "produtoDescricao6";
    private static final String PRODUTODESCRICAO7 = "produtoDescricao7";
    private static final String PRODUTODESCRICAO8 = "produtoDescricao8";
    private static final String PRODUTODESCRICAO9 = "produtoDescricao9";
    private static final String PRODUTODESCRICAO10 = "produtoDescricao10";
    private static final String PRODUTOIMAGEM1 = "produtoImagem1";
    private static final String PRODUTOIMAGEM2 = "produtoImagem2";
    private static final String PRODUTOIMAGEM3 = "produtoImagem3";
    private static final String PRODUTOIMAGEM4 = "produtoImagem4";
    private static final String PRODUTOESTADO = "produtoEstado";

    private final SQLiteDatabase database;

    public FixByteBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    public void createTableUser(SQLiteDatabase db){
        String createUsertable = "CREATE TABLE " + TABLE_NAME_USER +
                "(id INTEGER, " +
                USERNAME    + " TEXT NOT NULL, " +
                EMAIL     + " TEXT NOT NULL, " +
                ACCESSTOKEN + " TEXT NOT NULL, " +
                NOMEPROPRIO    + " TEXT NOT NULL, " +
                APELIDO     + " TEXT NOT NULL, " +
                DATANASC     + " TEXT NOT NULL, " +
                MORADA       + " TEXT NOT NULL " +
                ")";

        db.execSQL(createUsertable);
    }
    public void createTableCampanha(SQLiteDatabase db){
        String createCampanhatable = "CREATE TABLE " + TABLE_NAME + "(idCampanha INTEGER, " +
                CAMPANHANOME    + " TEXT NOT NULL, " +
                CAMPANHADATAINICIO     + " TEXT NOT NULL, " +
                CAMPANHADESCRICAO     + " TEXT NOT NULL, " +
                CAMPANHADATAFIM       + " TEXT NOT NULL " +
                ")";
        db.execSQL(createCampanhatable);
    }
    public void createTableCategoria(SQLiteDatabase db){
        String createCategoriatable = "CREATE TABLE " + TABLE_NAME_CATEGORIA + " ("+
                ID_CATEGORIAS     + " INTEGER NOT NULL, " +
                CATEGORIANOME    + " TEXT NOT NULL, " +
                CATEGORIADESCRICAO     + " TEXT NOT NULL, " +
                CATEGORIAESTADO     + " INTEGER NOT NULL, " +
                QNTPRODUTOS     + " INTEGER NOT NULL " +
                ")";

        db.execSQL(createCategoriatable);
    }
    public void createTableCategoriaChild(SQLiteDatabase db) {
        String createCategoriaChildtable = "CREATE TABLE " + TABLE_NAME_CATEGORIA_CHILD + " (" +
                IDCHILD     + " INTEGER NOT NULL, " +
                CHILDNOME    + " TEXT NOT NULL, " +
                CHILDDESCRICAO     + " TEXT NOT NULL, " +
                CATEGORIA_IDCATEGORIAS     + " TEXT NOT NULL, " +
                CHILDESTADO       + " INTEGER NOT NULL, " +
                QNTPRODUTOS     + " INTEGER NOT NULL " +
                ")";

        db.execSQL(createCategoriaChildtable);
    }
    public void createTableProduto(SQLiteDatabase db) {
        String createProdutotable = "CREATE TABLE " + TABLE_NAME_PRODUTO + " (" +
                IDPRODUTOS              + " INTEGER NOT NULL, " +
                PRODUTONOME             + " TEXT NOT NULL, " +
                PRODUTOCODIGO           + " TEXT NOT NULL, " +
                PRODUTODATACRIACAO      + " TEXT NOT NULL, " +
                PRODUTOSTOCK            + " INTEGER NOT NULL, " +
                PRODUTOPRECO            + " DECIMAL NOT NULL, " +
                PRODUTOMARCA            + " TEXT NOT NULL, " +
                CATEGORIA_CHILDID       + " LONG NOT NULL, " +
                PRODUTODESCRICAO1       + " TEXT , " +
                PRODUTODESCRICAO2       + " TEXT , " +
                PRODUTODESCRICAO3       + " TEXT , " +
                PRODUTODESCRICAO4       + " TEXT , " +
                PRODUTODESCRICAO5       + " TEXT , " +
                PRODUTODESCRICAO6       + " TEXT , " +
                PRODUTODESCRICAO7       + " TEXT , " +
                PRODUTODESCRICAO8       + " TEXT , " +
                PRODUTODESCRICAO9       + " TEXT , " +
                PRODUTODESCRICAO10      + " TEXT , " +
                PRODUTOIMAGEM1          + " TEXT , " +
                PRODUTOIMAGEM2          + " TEXT , " +
                PRODUTOIMAGEM3          + " TEXT , " +
                PRODUTOIMAGEM4          + " TEXT , " +
                PRODUTOESTADO           + " INTEGER NOT NULL" +
                ")";

        db.execSQL(createProdutotable);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableCampanha(db);
        createTableUser(db);
        createTableCategoria(db);
        createTableCategoriaChild(db);
        createTableProduto(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORIA);
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORIA_CHILD);
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRODUTO);
        this.onCreate(db);
    }

    //region campanhas
    public Campanha adicionarCampanhaBD(Campanha campanha){

        ContentValues values = new ContentValues();

        values.put(CAMPANHAID, campanha.getIdCampanha());
        values.put(CAMPANHANOME, campanha.getCampanhaNome());
        values.put(CAMPANHADATAINICIO, campanha.getCampanhaDataInicio());
        values.put(CAMPANHADESCRICAO, campanha.getCampanhaDescricao());
        values.put(CAMPANHADATAFIM, campanha.getCampanhaDataFim());

        this.database.insert(TABLE_NAME, null,values);
        return null;
    }

    public boolean editarCampanhaBD(Campanha campanha){
        ContentValues values = new ContentValues();
        values.put(CAMPANHANOME, campanha.getCampanhaNome());
        values.put(CAMPANHADATAINICIO, campanha.getCampanhaDataInicio());
        values.put(CAMPANHADESCRICAO, campanha.getCampanhaDescricao());
        values.put(CAMPANHADATAFIM, campanha.getCampanhaDataFim());

        return this.database.update(TABLE_NAME, values, "idCampanha = ?", new String[]{"" + campanha.getIdCampanha()})>0;
    }
    public boolean removerCampanhaBD (long idCampanha){
        return this.database.delete(TABLE_NAME, "idCampanha = ?", new String[]{"" + idCampanha})==1;

    }
    public ArrayList<Campanha> getAllCampanhasBD(){

        ArrayList<Campanha> campanhas = new ArrayList<>();
        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"idCampanha", CAMPANHANOME,CAMPANHADATAINICIO,CAMPANHADESCRICAO,CAMPANHADATAFIM},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                try {
                    String endDateString = cursor.getString(4);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date endDatedate = format.parse(endDateString);
                    if (System.currentTimeMillis() < endDatedate.getTime()){
                        Campanha nextCampanha = new Campanha(
                                cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4));
                        nextCampanha.setIdCampanha(cursor.getLong(0));
                        campanhas.add(nextCampanha);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }while(cursor.moveToNext());

        }
        return campanhas;
    }

    public void removeAllCampanhas(){
        this.database.delete(TABLE_NAME,null,null);
    }
    //endregion campanhas

    //region user
    public User adicionarUserBD(User user){

        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(EMAIL, user.getEmail());
        values.put(ACCESSTOKEN, user.getToken());
        values.put(NOMEPROPRIO, user.getUserNomeProprio());
        values.put(APELIDO, user.getUserApelido());
        values.put(DATANASC, user.getUserDataNasc());
        values.put(MORADA, user.getUserMorada());

        this.database.insert(TABLE_NAME_USER, null,values);

        return null;
    }
    public boolean editarUserBD(User user){
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(EMAIL, user.getEmail());
        values.put(ACCESSTOKEN, user.getToken());
        values.put(NOMEPROPRIO, user.getUserNomeProprio());
        values.put(APELIDO, user.getUserApelido());
        values.put(DATANASC, user.getUserDataNasc());
        values.put(MORADA, user.getUserMorada());

        return this.database.update(TABLE_NAME_USER, values, "id = ?", new String[]{"" + user.getId()})>0;
    }
    public boolean removerUserBD (long idUser){
        return this.database.delete(TABLE_NAME_USER, "id = ?", new String[]{"" + idUser})==1;

    }
    public ArrayList<User> getAllUsersBD(){
        ArrayList<User> users = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME_USER,new String[]{"id", USERNAME,EMAIL,ACCESSTOKEN,NOMEPROPRIO,APELIDO,DATANASC,MORADA},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                User nextUser = new User(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7));
                nextUser.setId(cursor.getLong(0));
                users.add(nextUser);
            }while(cursor.moveToNext());
        }
        return users;
    }
    public void removeAllUsers(){
        this.database.delete(TABLE_NAME_USER,null,null);
    }
    //endregion

    //region categorias
    public Categoria adicionarCategoriaBD(Categoria categoria){

        ContentValues values = new ContentValues();
        values.put(ID_CATEGORIAS, categoria.getIdcategorias());
        values.put(CATEGORIANOME, categoria.getCategoriaNome());
        values.put(CATEGORIADESCRICAO, categoria.getCategoriaDescricao());
        values.put(CATEGORIAESTADO, categoria.getCategoriaEstado());
        values.put(QNTPRODUTOS, categoria.getQntProdutos());

        long id = this.database.insert(TABLE_NAME_CATEGORIA, null,values);

        if (id > -1){
            categoria.setIdcategorias(categoria.getIdcategorias());
            return categoria;
        }

        return null;
    }

    public boolean editarCategoriaBD(Categoria categoria){
        ContentValues values = new ContentValues();
        values.put(ID_CATEGORIAS, categoria.getIdcategorias());
        values.put(CATEGORIANOME, categoria.getCategoriaNome());
        values.put(CATEGORIADESCRICAO, categoria.getCategoriaDescricao());
        values.put(CATEGORIAESTADO, categoria.getCategoriaEstado());
        values.put(QNTPRODUTOS, categoria.getQntProdutos());

        return this.database.update(TABLE_NAME_CATEGORIA, values, "idcategorias = ?", new String[]{"" + categoria.getIdcategorias()})>0;
    }

    public boolean removerCategoriaBD (long idcategorias){
        return this.database.delete(TABLE_NAME_CATEGORIA, "idcategorias = ?", new String[]{"" + idcategorias})==1;

    }

    public ArrayList<Categoria> getAllCategoriasBD(){
        ArrayList<Categoria> categorias = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME_CATEGORIA,new String[]{ID_CATEGORIAS, CATEGORIANOME,CATEGORIADESCRICAO,CATEGORIAESTADO,QNTPRODUTOS},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Categoria nextCategoria = new Categoria(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4));

                nextCategoria.setIdcategorias(cursor.getLong(0));
                categorias.add(nextCategoria);
            }while(cursor.moveToNext());

        }
        return categorias;
    }
    public void removeAllCategorias(){
        try
        {
            this.database.delete(TABLE_NAME_CATEGORIA,null,null);
        }
        catch (Exception Ex)
        {
            System.out.println("ERROR ON removeAllCategorias" + Ex);
        }
    }
    //endregion

    //region categoriasChild
    public CategoriaChild adicionarCategoriaChildBD(CategoriaChild categoriaChild){

        ContentValues values = new ContentValues();
        values.put(IDCHILD, categoriaChild.getIdchild());
        values.put(CHILDNOME, categoriaChild.getChildNome());
        values.put(CHILDDESCRICAO, categoriaChild.getChildDescricao());
        values.put(CATEGORIA_IDCATEGORIAS, categoriaChild.getCategoria_idcategorias());
        values.put(CHILDESTADO, categoriaChild.getChildEstado());
        values.put(QNTPRODUTOS, categoriaChild.getQntProdutos());


        long id = this.database.insert(TABLE_NAME_CATEGORIA_CHILD, null,values);

        if (id > -1){
            categoriaChild.setIdchild(categoriaChild.getIdchild());
            return categoriaChild;
        }
        return null;
    }

    public boolean editarCategoriaChildBD(CategoriaChild categoriaChild){
        ContentValues values = new ContentValues();
        values.put(IDCHILD, categoriaChild.getIdchild());
        values.put(CHILDNOME, categoriaChild.getChildNome());
        values.put(CHILDDESCRICAO, categoriaChild.getChildDescricao());
        values.put(CATEGORIA_IDCATEGORIAS, categoriaChild.getCategoria_idcategorias());
        values.put(CHILDESTADO, categoriaChild.getChildEstado());
        values.put(QNTPRODUTOS, categoriaChild.getQntProdutos());

        return this.database.update(TABLE_NAME_CATEGORIA_CHILD, values, "idchild = ?", new String[]{"" + categoriaChild.getIdchild()})>0;
    }

    public boolean removerCategoriaChildBD (long idchild){
        return this.database.delete(TABLE_NAME_CATEGORIA_CHILD, "idchild = ?", new String[]{"" + idchild})==1;
    }

    public ArrayList<CategoriaChild> getAllCategoriasChildBD(){
        ArrayList<CategoriaChild> categoriasChild = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME_CATEGORIA_CHILD,new String[]{IDCHILD, CHILDNOME,CHILDDESCRICAO,CATEGORIA_IDCATEGORIAS,CHILDESTADO,QNTPRODUTOS},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                CategoriaChild nextCategoriachild = new CategoriaChild(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3),
                        cursor.getInt(4),
                        cursor.getInt(5));

                nextCategoriachild.setIdchild(cursor.getLong(0));
                categoriasChild.add(nextCategoriachild);
            }while(cursor.moveToNext());

        }
        return categoriasChild;
    }

    public void removeAllCategoriasChild(){
        try
        {
            this.database.delete(TABLE_NAME_CATEGORIA_CHILD,null,null);
        }
        catch (Exception Ex)
        {
            System.out.println("ERROR ON removeAllCategoriasChild" + Ex);
        }
    }
    //endregion

    //region produtos
    public Produto adicionarProdutoBD(Produto produto){

        ContentValues values = new ContentValues();
        values.put(IDPRODUTOS, produto.getIdprodutos());
        values.put(PRODUTONOME, produto.getProdutoNome());
        values.put(PRODUTOCODIGO, produto.getProdutoCodigo());
        values.put(PRODUTODATACRIACAO, produto.getProdutoDataCriacao());
        values.put(PRODUTOSTOCK, produto.getProdutoStock());
        values.put(PRODUTOPRECO, produto.getProdutoPreco());
        values.put(PRODUTOMARCA, produto.getProdutoMarca());
        values.put(CATEGORIA_CHILDID, produto.getCategoria_child_id());
        values.put(PRODUTODESCRICAO1, produto.getProdutoDescricao1());
        values.put(PRODUTODESCRICAO2, produto.getProdutoDescricao2());
        values.put(PRODUTODESCRICAO3, produto.getProdutoDescricao3());
        values.put(PRODUTODESCRICAO4, produto.getProdutoDescricao4());
        values.put(PRODUTODESCRICAO5, produto.getProdutoDescricao5());
        values.put(PRODUTODESCRICAO6, produto.getProdutoDescricao6());
        values.put(PRODUTODESCRICAO7, produto.getProdutoDescricao7());
        values.put(PRODUTODESCRICAO8, produto.getProdutoDescricao8());
        values.put(PRODUTODESCRICAO9, produto.getProdutoDescricao9());
        values.put(PRODUTODESCRICAO10, produto.getProdutoDescricao10());
        values.put(PRODUTOIMAGEM1, produto.getProdutoImagem1());
        values.put(PRODUTOIMAGEM2, produto.getProdutoImagem2());
        values.put(PRODUTOIMAGEM3, produto.getProdutoImagem3());
        values.put(PRODUTOIMAGEM4, produto.getProdutoImagem4());
        values.put(PRODUTOESTADO, produto.getProdutoEstado());

        long id = this.database.insert(TABLE_NAME_PRODUTO, null,values);

        if (id > -1){
            produto.setIdprodutos(produto.getIdprodutos());
            return produto;
        }
        return null;
    }
    public boolean editarProdutoBD(Produto produto){
        ContentValues values = new ContentValues();
        values.put(IDPRODUTOS, produto.getIdprodutos());
        values.put(PRODUTONOME, produto.getProdutoNome());
        values.put(PRODUTOCODIGO, produto.getProdutoCodigo());
        values.put(PRODUTODATACRIACAO, produto.getProdutoDataCriacao());
        values.put(PRODUTOSTOCK, produto.getProdutoStock());
        values.put(PRODUTOPRECO, produto.getProdutoPreco());
        values.put(PRODUTOMARCA, produto.getProdutoMarca());
        values.put(CATEGORIA_CHILDID, produto.getCategoria_child_id());
        values.put(PRODUTODESCRICAO1, produto.getProdutoDescricao1());
        values.put(PRODUTODESCRICAO2, produto.getProdutoDescricao2());
        values.put(PRODUTODESCRICAO3, produto.getProdutoDescricao3());
        values.put(PRODUTODESCRICAO4, produto.getProdutoDescricao4());
        values.put(PRODUTODESCRICAO5, produto.getProdutoDescricao5());
        values.put(PRODUTODESCRICAO6, produto.getProdutoDescricao6());
        values.put(PRODUTODESCRICAO7, produto.getProdutoDescricao7());
        values.put(PRODUTODESCRICAO8, produto.getProdutoDescricao8());
        values.put(PRODUTODESCRICAO9, produto.getProdutoDescricao9());
        values.put(PRODUTODESCRICAO10, produto.getProdutoDescricao10());
        values.put(PRODUTOIMAGEM1, produto.getProdutoImagem1());
        values.put(PRODUTOIMAGEM2, produto.getProdutoImagem2());
        values.put(PRODUTOIMAGEM3, produto.getProdutoImagem3());
        values.put(PRODUTOIMAGEM4, produto.getProdutoImagem4());
        values.put(PRODUTOESTADO, produto.getProdutoEstado());

        return this.database.update(TABLE_NAME_PRODUTO, values, "idprodutos = ?", new String[]{"" + produto.getIdprodutos()})>0;
    }
    public boolean removerProdutoBD (long idprodutos){
        return this.database.delete(TABLE_NAME_PRODUTO, "idprodutos = ?", new String[]{"" + idprodutos})==1;
    }
    public ArrayList<Produto> getAllProdutosBD(){
        ArrayList<Produto> produtos = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME_PRODUTO,new String[]{
                IDPRODUTOS,PRODUTONOME,PRODUTOCODIGO, PRODUTODATACRIACAO,PRODUTOSTOCK,PRODUTOPRECO, PRODUTOMARCA,CATEGORIA_CHILDID,
                PRODUTODESCRICAO1,PRODUTODESCRICAO2,PRODUTODESCRICAO3, PRODUTODESCRICAO4,
                PRODUTODESCRICAO5,PRODUTODESCRICAO6, PRODUTODESCRICAO7, PRODUTODESCRICAO8,
                PRODUTODESCRICAO9,PRODUTODESCRICAO10,PRODUTOIMAGEM1,PRODUTOIMAGEM2,PRODUTOIMAGEM3,
                PRODUTOIMAGEM4, PRODUTOESTADO},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Produto nextProduto = new Produto(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getFloat(5),
                        cursor.getString(6),
                        cursor.getLong(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getString(13),
                        cursor.getString(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getString(18),
                        cursor.getString(19),
                        cursor.getString(20),
                        cursor.getString(21),
                        cursor.getInt(22));

                nextProduto.setIdprodutos(cursor.getLong(0));
                produtos.add(nextProduto);
            }while(cursor.moveToNext());

        }
        return produtos;
    }
    public void removeAllProdutos(){
        try
        {
            this.database.delete(TABLE_NAME_PRODUTO,null,null);
        }
        catch (Exception Ex)
        {
            System.out.println("ERROR ON removeAllProdutos" + Ex);
        }
    }
    //endregion
}

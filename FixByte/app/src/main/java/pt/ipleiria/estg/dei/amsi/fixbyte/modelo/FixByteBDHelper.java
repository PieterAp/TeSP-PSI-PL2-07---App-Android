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
    private static final String CATEGORIANOME = "categoriaNome";
    private static final String CATEGORIADESCRICAO = "categoriaDescricao";
    private static final String CATEGORIAESTADO = "categoriaEstado";
    private static final String QNTPRODUTOS = "qntProdutos";

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
        String createCategoriatable = "CREATE TABLE " + TABLE_NAME_CATEGORIA + "(idcategorias INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CATEGORIANOME    + " TEXT NOT NULL, " +
                CATEGORIADESCRICAO     + " TEXT NOT NULL, " +
                CATEGORIAESTADO     + " INTEGER NOT NULL, " +
                QNTPRODUTOS     + " INTEGER NOT NULL " +
                ")";

        db.execSQL(createCategoriatable);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableCampanha(db);
        createTableUser(db);
        createTableCategoria(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        this.onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CATEGORIA);
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

        return this.database.update(TABLE_NAME, values, "id = ?", new String[]{"" + user.getId()})>0;
    }
    public boolean removerUserBD (long idUser){
        return this.database.delete(TABLE_NAME, "id = ?", new String[]{"" + idUser})==1;

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
        values.put(CATEGORIANOME, categoria.getCategoriaNome());
        values.put(CATEGORIADESCRICAO, categoria.getCategoriaDescricao());
        values.put(CATEGORIAESTADO, categoria.getCategoriaEstado());
        values.put(QNTPRODUTOS, categoria.getQntProdutos());

        long id = this.database.insert(TABLE_NAME_CATEGORIA, null,values);

        if (id > -1){
            categoria.setIdcategorias(id);
            return categoria;
        }
        return null;
    }
    public boolean editarCategoriaBD(Categoria categoria){
        ContentValues values = new ContentValues();
        values.put(CATEGORIANOME, categoria.getCategoriaNome());
        values.put(CATEGORIADESCRICAO, categoria.getCategoriaDescricao());
        values.put(CATEGORIAESTADO, categoria.getCategoriaEstado());
        values.put(QNTPRODUTOS, categoria.getQntProdutos());

        return this.database.update(TABLE_NAME, values, "idcategorias = ?", new String[]{"" + categoria.getIdcategorias()})>0;
    }
    public boolean removerCategoriaBD (long idcategorias){
        return this.database.delete(TABLE_NAME_CATEGORIA, "idcategorias = ?", new String[]{"" + idcategorias})==1;

    }
    public ArrayList<Categoria> getAllCategoriasBD(){
        ArrayList<Categoria> categorias = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME_CATEGORIA,new String[]{"idcategorias", CATEGORIANOME,CATEGORIADESCRICAO,CATEGORIAESTADO,QNTPRODUTOS},null,null,null,null,null);

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
}

package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserdataBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "Userdata";

    private static final String NOMEPROPRIO = "userNomeProprio";
    private static final String APELIDO = "userApelido";
    private static final String ESTADO = "userEstado";
    private static final String MORADA = "userMorada";
    private static final String USER_ID = "user_id";
    private static final String VISIBILIDADE = "userVisibilidade";

    private final SQLiteDatabase database;

    public UserdataBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserdatatable = "CREATE TABLE " + TABLE_NAME + "(iduser INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOMEPROPRIO    + " TEXT NOT NULL, " +
                APELIDO     + " TEXT NOT NULL, " +
                ESTADO     + " TEXT NOT NULL, " +
                MORADA       + " TEXT NOT NULL, " +
                USER_ID      + " LONG NOT NULL," +
                VISIBILIDADE      + " INTEGER NOT NULL" +
                ")";

        db.execSQL(createUserdatatable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public Userdata adicionarUserdataBD(Userdata userdata){

        ContentValues values = new ContentValues();
        values.put(NOMEPROPRIO, userdata.getUserNomeProprio());
        values.put(APELIDO, userdata.getUserApelido());
        values.put(ESTADO, userdata.getUserEstado());
        values.put(MORADA, userdata.getUserMorada());
        values.put(USER_ID, userdata.getUser_id());
        values.put(VISIBILIDADE, userdata.getUserVisibilidade());


        long id = this.database.insert(TABLE_NAME, null,values);

        if (id > -1){
            userdata.setIduser(id);
            return userdata;
        }
        return null;
    }
    public boolean editarUserdataBD(Userdata userdata){
        ContentValues values = new ContentValues();
        values.put(NOMEPROPRIO, userdata.getUserNomeProprio());
        values.put(APELIDO, userdata.getUserApelido());
        values.put(ESTADO, userdata.getUserEstado());
        values.put(MORADA, userdata.getUserMorada());
        values.put(USER_ID, userdata.getUser_id());
        values.put(VISIBILIDADE, userdata.getUserVisibilidade());

        return this.database.update(TABLE_NAME, values, "iduser = ?", new String[]{"" + userdata.getIduser()})>0;
    }
    public boolean removerLivroBD (long idLivro){
        return this.database.delete(TABLE_NAME, "iduser = ?", new String[]{"" + idLivro})==1;

    }
    public ArrayList<Userdata> getAllUsersdataBD(){
        ArrayList<Userdata> usersdata = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"iduser", NOMEPROPRIO,APELIDO,ESTADO,MORADA,USER_ID,VISIBILIDADE},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Userdata nextUserdata = new Userdata(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getLong(7),
                        cursor.getInt(8));
                nextUserdata.setIduser(cursor.getLong(0));
                usersdata.add(nextUserdata);
            }while(cursor.moveToNext());

        }
        return usersdata;
    }

    public void removeAllUsersdata(){
        this.database.delete(TABLE_NAME,null,null);
    }

}

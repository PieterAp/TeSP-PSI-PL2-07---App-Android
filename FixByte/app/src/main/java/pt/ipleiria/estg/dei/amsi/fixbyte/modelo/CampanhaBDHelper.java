package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CampanhaBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "campanha";

    private static final String CAMPANHANOME = "campanhaNome";
    private static final String CAMPANHADATAINICIO = "campanhaDataInicio";
    private static final String CAMPANHADATAFIM = "campanhaDataFim";
    private static final String CAMPANHADESCRICAO = "campanhaDescricao";

    private final SQLiteDatabase database;

    public CampanhaBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCampanhatable = "CREATE TABLE " + TABLE_NAME + "(idCampanha INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CAMPANHANOME    + " TEXT NOT NULL, " +
                CAMPANHADATAINICIO     + " TEXT NOT NULL, " +
                CAMPANHADATAFIM     + " TEXT NOT NULL, " +
                CAMPANHADESCRICAO       + " TEXT NOT NULL " +
                ")";

        db.execSQL(createCampanhatable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public Campanha adicionarCampanhaBD(Campanha campanha){

        ContentValues values = new ContentValues();
        values.put(CAMPANHANOME, campanha.getCampanhaNome());
        values.put(CAMPANHADATAINICIO, campanha.getCampanhaDataInicio());
        values.put(CAMPANHADATAFIM, campanha.getCampanhaDataFim());
        values.put(CAMPANHADESCRICAO, campanha.getCampanhaDescricao());

        long id = this.database.insert(TABLE_NAME, null,values);

        if (id > -1){
            campanha.setIdCampanha(id);
            return campanha;
        }
        return null;
    }

    public boolean editarLivroBD(Campanha campanha){
        ContentValues values = new ContentValues();
        values.put(CAMPANHANOME, campanha.getCampanhaNome());
        values.put(CAMPANHADATAINICIO, campanha.getCampanhaDataInicio());
        values.put(CAMPANHADATAFIM, campanha.getCampanhaDataFim());
        values.put(CAMPANHADESCRICAO, campanha.getCampanhaDescricao());

        return this.database.update(TABLE_NAME, values, "idCampanha = ?", new String[]{"" + campanha.getIdCampanha()})>0;
    }
    public boolean removerLivroBD (long idCampanha){
        return this.database.delete(TABLE_NAME, "idCampanha = ?", new String[]{"" + idCampanha})==1;

    }
    public ArrayList<Campanha> getAllCampanhasBD(){

        ArrayList<Campanha> campanhas = new ArrayList<>();
        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"idCampanha", CAMPANHANOME,CAMPANHADATAINICIO,CAMPANHADATAFIM,CAMPANHADESCRICAO},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Campanha nextCampanha = new Campanha(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                nextCampanha.setIdCampanha(cursor.getLong(0));

                campanhas.add(nextCampanha);
            }while(cursor.moveToNext());

        }
        return campanhas;
    }

    public void removeAllCampanhas(){
        this.database.delete(TABLE_NAME,null,null);
    }

}

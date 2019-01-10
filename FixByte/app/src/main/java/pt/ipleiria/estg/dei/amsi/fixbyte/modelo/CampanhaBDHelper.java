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

public class CampanhaBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "campanha";

    private static final String CAMPANHAID = "idCampanha";
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
        String createCampanhatable = "CREATE TABLE " + TABLE_NAME + "(idCampanha INTEGER, " +
                CAMPANHANOME    + " TEXT NOT NULL, " +
                CAMPANHADATAINICIO     + " TEXT NOT NULL, " +
                CAMPANHADESCRICAO     + " TEXT NOT NULL, " +
                CAMPANHADATAFIM       + " TEXT NOT NULL " +
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

}

package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ReparacaoBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "reparacao";

    private static final String REPARACAONOME = "reparacaoNome";
    private static final String REPARACAOESTADO = "reparacaoEstado";
    private static final String REPARACAONUMERO = "reparacaoNumero";
    private static final String REPARACAODATA = "reparacaoData";
    private static final String REPARACAODATACONCLUIDO = "reparacaoDataConcluido";
    private static final String REPARACAODESCRICAO = "reparacaoDescricao";
    private static final String USER_IDUSER = "user_iduser";

    private final SQLiteDatabase database;

    public ReparacaoBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createReparacaotable = "CREATE TABLE " + TABLE_NAME + "(idreparacao INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REPARACAONOME    + " TEXT NOT NULL, " +
                REPARACAOESTADO     + " TEXT NOT NULL, " +
                REPARACAONUMERO     + " LONG NOT NULL, " +
                REPARACAODATA       + " TEXT NOT NULL, " +
                REPARACAODATACONCLUIDO       + " TEXT NOT NULL, " +
                REPARACAODESCRICAO       + " TEXT NOT NULL, " +
                USER_IDUSER       + " TEXT NOT NULL " +
                ")";

        db.execSQL(createReparacaotable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public Reparacao adicionarReparacaoBD(Reparacao reparacao){

        ContentValues values = new ContentValues();
        values.put(REPARACAONOME, reparacao.getReparacaoNome());
        values.put(REPARACAOESTADO, reparacao.getReparacaoEstado());
        values.put(REPARACAONUMERO, reparacao.getReparacaoNumero());
        values.put(REPARACAODATA, reparacao.getReparacaoData());
        values.put(REPARACAODATACONCLUIDO, reparacao.getReparacaoDataConcluido());
        values.put(REPARACAODESCRICAO, reparacao.getReparacaoDescricao());
        values.put(USER_IDUSER, reparacao.getUser_iduser());


        long id = this.database.insert(TABLE_NAME, null,values);

        if (id > -1){
            reparacao.setIdreparacao(id);
            return reparacao;
        }
        return null;
    }
    public boolean editarReparacaoBD(Reparacao reparacao){
        ContentValues values = new ContentValues();
        values.put(REPARACAONOME, reparacao.getReparacaoNome());
        values.put(REPARACAOESTADO, reparacao.getReparacaoEstado());
        values.put(REPARACAONUMERO, reparacao.getReparacaoNumero());
        values.put(REPARACAODATA, reparacao.getReparacaoData());
        values.put(REPARACAODATACONCLUIDO, reparacao.getReparacaoDataConcluido());
        values.put(REPARACAODESCRICAO, reparacao.getReparacaoDescricao());
        values.put(USER_IDUSER, reparacao.getUser_iduser());


        return this.database.update(TABLE_NAME, values, "idreparacao = ?", new String[]{"" + reparacao.getIdreparacao()})>0;
    }
    public boolean removerReparacaoBD (long idreparacao){
        return this.database.delete(TABLE_NAME, "idreparacao = ?", new String[]{"" + idreparacao})==1;

    }
    public ArrayList<Reparacao> getAllReparacoesBD(){
        ArrayList<Reparacao> reparacoes = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"idreparacao", REPARACAONOME,REPARACAOESTADO,REPARACAONUMERO,REPARACAODATA,REPARACAODATACONCLUIDO,REPARACAODESCRICAO,USER_IDUSER},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Reparacao nextReparacao = new Reparacao(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getLong(5),
                        cursor.getString(6),
                        cursor.getLong(7));

                nextReparacao.setIdreparacao(cursor.getLong(0));
                reparacoes.add(nextReparacao);
            }while(cursor.moveToNext());

        }
        return reparacoes;
    }
    public void removeAllReparacoes(){
        this.database.delete(TABLE_NAME,null,null);
    }
}

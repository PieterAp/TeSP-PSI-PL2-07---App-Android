package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class CompraBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "compra";

    private static final String COMPRADATA = "compraData";
    private static final String USER_IDUSER = "user_iduser";
    private static final String COMPRAVALOR = "compraValor";
    private static final String COMPRAESTADO = "compraEstado";

    private final SQLiteDatabase database;

    public CompraBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCompratable = "CREATE TABLE " + TABLE_NAME + "(idCompra INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COMPRADATA    + " TEXT NOT NULL, " +
                USER_IDUSER     + " LONG NOT NULL, " +
                COMPRAVALOR     + " DECIMAL NOT NULL, " +
                COMPRAESTADO       + " INTEGER NOT NULL " +
                ")";

        db.execSQL(createCompratable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public Compra adicionarCompraBD(Compra compra){

        ContentValues values = new ContentValues();
        values.put(COMPRADATA, compra.getCompraData());
        values.put(USER_IDUSER, compra.getUser_iduser());
        values.put(COMPRAVALOR, compra.getCompraValor());
        values.put(COMPRAESTADO, compra.getCompraEstado());

        long id = this.database.insert(TABLE_NAME, null,values);

        if (id > -1){
            compra.setIdCompra(id);
            return compra;
        }
        return null;
    }
    public boolean editarCompraBD(Compra compra){
        ContentValues values = new ContentValues();
        values.put(COMPRADATA, compra.getCompraData());
        values.put(USER_IDUSER, compra.getUser_iduser());
        values.put(COMPRAVALOR, compra.getCompraValor());
        values.put(COMPRAESTADO, compra.getCompraEstado());

        return this.database.update(TABLE_NAME, values, "idCompra = ?", new String[]{"" + compra.getIdCompra()})>0;
    }
    public boolean removerCompraBD (long idLivro){
        return this.database.delete(TABLE_NAME, "idCompra = ?", new String[]{"" + idLivro})==1;

    }
    public ArrayList<Compra> getAllComprasBD(){
        ArrayList<Compra> compras = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"idCompra", COMPRADATA,USER_IDUSER,COMPRAVALOR,COMPRAESTADO},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Compra nextCompra = new Compra(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getLong(2),
                        cursor.getFloat(3),
                        cursor.getInt(4));

                nextCompra.setIdCompra(cursor.getLong(0));
                compras.add(nextCompra);
            }while(cursor.moveToNext());

        }
        return compras;
    }
    public void removeAllCompras(){
        this.database.delete(TABLE_NAME,null,null);
    }

}

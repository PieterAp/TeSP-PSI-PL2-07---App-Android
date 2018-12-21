package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProdutoCampanhaBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "produtocampanha";

    private static final String PRODUTOS_IDPRODUTOS = "produtos_idprodutos";
    private static final String CAMPANHA_IDCAMPANHA = "campanha_idCampanha";
    private static final String CAMPANHAPERCENTAGEM = "campanhaPercentagem";

    private final SQLiteDatabase database;

    public ProdutoCampanhaBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createProdutoCampanhatable = "CREATE TABLE " + TABLE_NAME + "(idprodutocampanha INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUTOS_IDPRODUTOS    + " LONG NOT NULL, " +
                CAMPANHA_IDCAMPANHA     + " LONG NOT NULL, " +
                CAMPANHAPERCENTAGEM     + " INT NOT NULL " +
                ")";

        db.execSQL(createProdutoCampanhatable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public ProdutoCampanha adicionarProdutoCampanhaBD(ProdutoCampanha produtocampanha){

        ContentValues values = new ContentValues();
        values.put(PRODUTOS_IDPRODUTOS, produtocampanha.getProdutos_idprodutos());
        values.put(CAMPANHA_IDCAMPANHA, produtocampanha.getCampanha_idCampanha());
        values.put(CAMPANHAPERCENTAGEM, produtocampanha.getCampanhaPercentagem());


        long id = this.database.insert(TABLE_NAME, null,values);

        if (id > -1){
            produtocampanha.setIdprodutocampanha(id);
            return produtocampanha;
        }
        return null;
    }
    public boolean editarProdutoCampanhaBD(ProdutoCampanha produtocampanha){
        ContentValues values = new ContentValues();
        values.put(PRODUTOS_IDPRODUTOS, produtocampanha.getProdutos_idprodutos());
        values.put(CAMPANHA_IDCAMPANHA, produtocampanha.getCampanha_idCampanha());
        values.put(CAMPANHAPERCENTAGEM, produtocampanha.getCampanhaPercentagem());


        return this.database.update(TABLE_NAME, values, "idprodutocampanha = ?", new String[]{"" + produtocampanha.getIdprodutocampanha()})>0;
    }
    public boolean removerProdutoCampanhaBD (long idprodutocampanha){
        return this.database.delete(TABLE_NAME, "idprodutocampanha = ?", new String[]{"" + idprodutocampanha})==1;

    }
    public ArrayList<ProdutoCampanha> getAllProdutoCampanhasBD(){
        ArrayList<ProdutoCampanha> produtoscampanha = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"idprodutocampanha", PRODUTOS_IDPRODUTOS,CAMPANHA_IDCAMPANHA,CAMPANHAPERCENTAGEM},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                ProdutoCampanha nextProdutocampanha = new ProdutoCampanha(
                        cursor.getLong(0),
                        cursor.getLong(1),
                        cursor.getLong(2),
                        cursor.getInt(3));


                        nextProdutocampanha.setIdprodutocampanha(cursor.getLong(0));
                produtoscampanha.add(nextProdutocampanha);
            }while(cursor.moveToNext());

        }
        return produtoscampanha;
    }
    public void removeAllProdutosCampanha(){
        this.database.delete(TABLE_NAME,null,null);
    }

}

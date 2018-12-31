package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class CompraprodutoBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "compraproduto";

    private static final String COMPRA_IDCOMPRAS = "compra_idcompra";
    private static final String PRODUTO_IDPRODUTOS = "produto_idprodutos";
    private static final String PRODUTOPRECO = "produto_preco";

    private final SQLiteDatabase database;

    public CompraprodutoBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCompraprodutotable = "CREATE TABLE " + TABLE_NAME + "( " +
                COMPRA_IDCOMPRAS    + " INT NOT NULL, " +
                PRODUTO_IDPRODUTOS     + " INT NOT NULL, " +
                PRODUTOPRECO     + " DECIMAL NOT NULL " +
                ")";

        db.execSQL(createCompraprodutotable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public Compraproduto adicionarCompraProdutooBD(Compraproduto compraproduto){

        ContentValues values = new ContentValues();
        values.put(COMPRA_IDCOMPRAS, compraproduto.getCompra_idcompras());
        values.put(PRODUTO_IDPRODUTOS, compraproduto.getProduto_idprodutos());
        values.put(PRODUTOPRECO, compraproduto.getProduto_preco());

        long id = this.database.insert(TABLE_NAME, null,values);

        /*if (id > -1){
            compraproduto.setId(id);
            return compraproduto;
        }*/
        return null;
    }
    public boolean editarCompraprodutoBD(Compraproduto compraproduto){
        ContentValues values = new ContentValues();
        values.put(COMPRA_IDCOMPRAS, compraproduto.getCompra_idcompras());
        values.put(PRODUTO_IDPRODUTOS, compraproduto.getProduto_idprodutos());
        values.put(PRODUTOPRECO, compraproduto.getProduto_preco());


        return this.database.update(TABLE_NAME, values, "compra_idcompras = ?, ", new String[]{"" + compraproduto.getCompra_idcompras()})>0;
    }
    public boolean removerCompraprodutoBD (long compraid, long produtoid,float produtoPreco){
        //return this.database.delete(TABLE_NAME, "id = ?", new String[]{"" + idLivro})==1;
        return false;
    }
    public ArrayList<Compraproduto> getAllCompraprodutoBD(){
        ArrayList<Compraproduto> compraprodutos = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"compra_idcompras", COMPRA_IDCOMPRAS,PRODUTO_IDPRODUTOS,PRODUTOPRECO},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Compraproduto nextCompraproduto = new Compraproduto(
                        cursor.getLong(0),
                        cursor.getLong(1),
                        cursor.getFloat(2));
                compraprodutos.add(nextCompraproduto);
            }while(cursor.moveToNext());

        }
        return compraprodutos;
    }
    public void removeAllCompraprodutos(){
        this.database.delete(TABLE_NAME,null,null);
    }

}

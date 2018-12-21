package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProdutoBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "produto";

    private static final String PRODUTONOME = "produtoNome";
    private static final String PRODUTOCODIGO = "produtoCodigo";
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

    public ProdutoBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createProdutotable = "CREATE TABLE " + TABLE_NAME + "(idprodutos INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUTONOME             + " TEXT NOT NULL, " +
                PRODUTOCODIGO           + " TEXT NOT NULL, " +
                PRODUTOSTOCK            + " INTEGER NOT NULL, " +
                PRODUTOPRECO            + " DECIMAL NOT NULL, " +
                PRODUTOMARCA            + " TEXT NOT NULL, " +
                CATEGORIA_CHILDID       + " LONG NOT NULL, " +
                PRODUTODESCRICAO1       + " TEXT NOT NULL, " +
                PRODUTODESCRICAO2       + " TEXT NOT NULL, " +
                PRODUTODESCRICAO3       + " TEXT NOT NULL, " +
                PRODUTODESCRICAO4       + " TEXT NOT NULL, " +
                PRODUTODESCRICAO5       + " TEXT NOT NULL, " +
                PRODUTODESCRICAO6       + " TEXT NOT NULL, " +
                PRODUTODESCRICAO7       + " TEXT NOT NULL, " +
                PRODUTODESCRICAO8       + " TEXT NOT NULL, " +
                PRODUTODESCRICAO9       + " TEXT NOT NULL, " +
                PRODUTODESCRICAO10      + " TEXT NOT NULL, " +
                PRODUTOIMAGEM1          + " TEXT NOT NULL, " +
                PRODUTOIMAGEM2          + " TEXT NOT NULL, " +
                PRODUTOIMAGEM3          + " TEXT NOT NULL, " +
                PRODUTOIMAGEM4          + " TEXT NOT NULL, " +
                PRODUTOESTADO           + " INTEGER NOT NULL " +
                ")";

        db.execSQL(createProdutotable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public Produto adicionarProdutoBD(Produto produto){

        ContentValues values = new ContentValues();
        values.put(PRODUTONOME, produto.getProdutoNome());
        values.put(PRODUTOCODIGO, produto.getProdutoCodigo());
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

        long id = this.database.insert(TABLE_NAME, null,values);

        if (id > -1){
            produto.setIdprodutos(id);
            return produto;
        }
        return null;
    }
    public boolean editarProdutoBD(Produto produto){
        ContentValues values = new ContentValues();
        values.put(PRODUTONOME, produto.getProdutoNome());
        values.put(PRODUTOCODIGO, produto.getProdutoCodigo());
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

        return this.database.update(TABLE_NAME, values, "idprodutos = ?", new String[]{"" + produto.getIdprodutos()})>0;
    }
    public boolean removerProdutoBD (long idprodutos){
        return this.database.delete(TABLE_NAME, "idprodutos = ?", new String[]{"" + idprodutos})==1;

    }
    public ArrayList<Produto> getAllProdutosBD(){
        ArrayList<Produto> produtos = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"idprodutos",
                PRODUTONOME,PRODUTOCODIGO,PRODUTOSTOCK,PRODUTOPRECO, PRODUTOMARCA,CATEGORIA_CHILDID,
                PRODUTODESCRICAO1,PRODUTODESCRICAO2,PRODUTODESCRICAO3, PRODUTODESCRICAO4,
                PRODUTODESCRICAO5,PRODUTODESCRICAO6, PRODUTODESCRICAO7, PRODUTODESCRICAO8,
                PRODUTODESCRICAO9,PRODUTODESCRICAO10,PRODUTOIMAGEM1,PRODUTOIMAGEM2,PRODUTOIMAGEM3,
                PRODUTOIMAGEM4},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Produto nextProduto = new Produto(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getFloat(4),
                        cursor.getString(5),
                        cursor.getLong(6),
                        cursor.getString(7),
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
                        cursor.getInt(21));

                nextProduto.setIdprodutos(cursor.getLong(0));
                produtos.add(nextProduto);
            }while(cursor.moveToNext());

        }
        return produtos;
    }
    public void removeAllProdutos(){
        this.database.delete(TABLE_NAME,null,null);
    }

}

package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class CategoriaBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "categoria";

    private static final String CATEGORIANOME = "categoriaNome";
    private static final String CATEGORIADESCRICAO = "categoriaDescricao";
    private static final String CATEGORIAESTADO = "categoriaEstado";

    private final SQLiteDatabase database;

    public CategoriaBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCategoriatable = "CREATE TABLE " + TABLE_NAME + "(idcategorias INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CATEGORIANOME    + " TEXT NOT NULL, " +
                CATEGORIADESCRICAO     + " TEXT NOT NULL, " +
                CATEGORIAESTADO     + " INTEGER NOT NULL " +
                ")";

        db.execSQL(createCategoriatable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public Categoria adicionarCategoriaBD(Categoria categoria){

        ContentValues values = new ContentValues();
        values.put(CATEGORIANOME, categoria.getCategoriaNome());
        values.put(CATEGORIADESCRICAO, categoria.getCategoriaDescricao());
        values.put(CATEGORIAESTADO, categoria.getCategoriaEstado());

        long id = this.database.insert(TABLE_NAME, null,values);

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

        return this.database.update(TABLE_NAME, values, "idcategorias = ?", new String[]{"" + categoria.getIdcategorias()})>0;
    }

    public boolean removerCategoriaBD (long idcategorias){
        return this.database.delete(TABLE_NAME, "idcategorias = ?", new String[]{"" + idcategorias})==1;

    }

    public ArrayList<Categoria> getAllCategoriasBD(){
        ArrayList<Categoria> categorias = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"idcategorias", CATEGORIANOME,CATEGORIADESCRICAO,CATEGORIAESTADO},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                Categoria nextCategoria = new Categoria(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3));

                nextCategoria.setIdcategorias(cursor.getLong(0));
                categorias.add(nextCategoria);
            }while(cursor.moveToNext());

        }
        return categorias;
    }

    public void removeAllCategorias()
    {
        this.database.delete(TABLE_NAME,null,null);
    }
}

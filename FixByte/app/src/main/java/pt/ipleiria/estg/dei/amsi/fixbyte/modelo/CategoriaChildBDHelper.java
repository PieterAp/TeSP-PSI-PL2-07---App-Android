package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class CategoriaChildBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "categoria_child";

    private static final String CHILDNOME = "childNome";
    private static final String CHILDDESCRICAO = "childDescricao";
    private static final String CATEGORIA_IDCATEGORIAS = "categoria_idcategorias";
    private static final String CHILDESTADO = "childEstado";


    private final SQLiteDatabase database;

    public CategoriaChildBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createLivrotable = "CREATE TABLE " + TABLE_NAME + "(idchild INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CHILDNOME    + " TEXT NOT NULL, " +
                CHILDDESCRICAO     + " TEXT NOT NULL, " +
                CATEGORIA_IDCATEGORIAS     + " TEXT NOT NULL, " +
                CHILDESTADO       + " INTEGER NOT NULL " +
                ")";

        db.execSQL(createLivrotable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public CategoriaChild adicionarCategoriaChildBD(CategoriaChild categoriachild){

        ContentValues values = new ContentValues();
        values.put(CHILDNOME, categoriachild.getChildNome());
        values.put(CHILDDESCRICAO, categoriachild.getChildDescricao());
        values.put(CATEGORIA_IDCATEGORIAS, categoriachild.getCategoria_idcategorias());
        values.put(CHILDESTADO, categoriachild.getChildEstado());

        long id = this.database.insert(TABLE_NAME, null,values);

        if (id > -1){
            categoriachild.setIdchild(id);
            return categoriachild;
        }
        return null;
    }
    public boolean editarCategoriaChildBD(CategoriaChild categoriachild){
        ContentValues values = new ContentValues();
        values.put(CHILDNOME, categoriachild.getChildNome());
        values.put(CHILDDESCRICAO, categoriachild.getChildDescricao());
        values.put(CATEGORIA_IDCATEGORIAS, categoriachild.getCategoria_idcategorias());
        values.put(CHILDESTADO, categoriachild.getChildEstado());

        return this.database.update(TABLE_NAME, values, "idchild = ?", new String[]{"" + categoriachild.getIdchild()})>0;
    }
    public boolean removerLivroBD (long idLivro){
        return this.database.delete(TABLE_NAME, "idchild = ?", new String[]{"" + idLivro})==1;

    }
    public ArrayList<CategoriaChild> getAllCategoriasChildBD(){
        ArrayList<CategoriaChild> categoriasChild = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"idchild", CHILDNOME,CHILDDESCRICAO,CATEGORIA_IDCATEGORIAS,CHILDESTADO},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                CategoriaChild nextCategoriachild = new CategoriaChild(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3),
                        cursor.getInt(4));

                nextCategoriachild.setIdchild(cursor.getLong(0));
                categoriasChild.add(nextCategoriachild);
            }while(cursor.moveToNext());

        }
        return categoriasChild;
    }
    public void removeAllLivros(){
        this.database.delete(TABLE_NAME,null,null);
    }

}

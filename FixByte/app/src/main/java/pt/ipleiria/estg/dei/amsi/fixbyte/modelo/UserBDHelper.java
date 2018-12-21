package pt.ipleiria.estg.dei.amsi.fixbyte.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserBDHelper extends SQLiteOpenHelper {
    private static  final int DB_VERSION =1;
    private static final String DB_NAME = "tesp-psi-pl2-07-web";
    private static final String TABLE_NAME = "User";

    private static final String USERNAME = "username";
    private static final String PASSWORDHASH = "password_hash";
    private static final String EMAIL = "email";

    private final SQLiteDatabase database;

    public UserBDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }
    public void onCreate(SQLiteDatabase db) {
        String createUsertable = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME    + " TEXT NOT NULL, " +
                PASSWORDHASH     + " TEXT NOT NULL, " +
                EMAIL     + " TEXT NOT NULL " +
                ")";

        db.execSQL(createUsertable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public User adicionarUserBD(User user){

        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORDHASH, user.getPassword_hash());
        values.put(EMAIL, user.getEmail());

        long id = this.database.insert(TABLE_NAME, null,values);

        if (id > -1){
            user.setId(id);
            return user;
        }
        return null;
    }
    public boolean editarUserBD(User user){
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORDHASH, user.getPassword_hash());
        values.put(EMAIL, user.getEmail());

        return this.database.update(TABLE_NAME, values, "id = ?", new String[]{"" + user.getId()})>0;
    }
    public boolean removerUserBD (long idUser){
        return this.database.delete(TABLE_NAME, "id = ?", new String[]{"" + idUser})==1;

    }
    public ArrayList<User> getAllUsersBD(){
        ArrayList<User> users = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,new String[]{"id", USERNAME,PASSWORDHASH,EMAIL},null,null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                User nextUser = new User(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                nextUser.setId(cursor.getLong(0));
                users.add(nextUser);
            }while(cursor.moveToNext());

        }
        return users;
    }
    public void removeAllUsers(){
        this.database.delete(TABLE_NAME,null,null);
    }
}

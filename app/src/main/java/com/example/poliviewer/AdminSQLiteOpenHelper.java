package com.example.poliviewer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bd_version2";
    public static final String TABLA_NAME = "login";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_USUARIO = "usuario";
    public static final String COLUMNA_CONTRASEÑA = "contraseña";




    public static final String SQL_CREAR = "create table "
            + TABLA_NAME + " (" + COLUMNA_ID
            + " INTEGER primary key autoincrement, "+ COLUMNA_USUARIO
            + " TEXT not null, "+ COLUMNA_CONTRASEÑA
            + " TEXT not null)";

    public static final String SQL_REGISTROS = "create table "
            + "registros" + " (" + "id"
            + " INTEGER primary key autoincrement, "+ "cedula"
            + " TEXT not null, "+ "nombres"
            + " TEXT not null, "+ "apellidos"
            + " TEXT not null, "+ "telefono"
            + " TEXT not null, "+ "correo"
            + " INTEGER not null, "+ "idevento"
            + " INTEGER not null, "+ "idhorario"
            + " INTEGER not null, "+ "espectador"
            + " TEXT not null)";

    public static final String SQL_CREAREVENTO = "create table "
            + "crearevento" + " (" + "id"
            + " INTEGER primary key autoincrement, "+ "titulo"
            + " TEXT not null, "+ "descripcion"
            + " TEXT not null, "+ "tipodeevento"
            + " TEXT not null, "+ "direccionevento"
            + " TEXT not null, "+ "fecha"
            + " TEXT not null, "+ "hora"
            + " TEXT not null)";

    public static final String SQL_SOPORTE = "create table "
            + "soporte" + " (" + "id"
            + " INTEGER primary key autoincrement, "+ "tema"
            + " TEXT not null, "+ "descripcion"
            + " TEXT not null)";


    public AdminSQLiteOpenHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR);
        db.execSQL(SQL_REGISTROS);
        db.execSQL(SQL_CREAREVENTO);
        db.execSQL(SQL_SOPORTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS login");
        db.execSQL("DROP TABLE IF EXISTS registros");
        db.execSQL("DROP TABLE IF EXISTS crearevento");
        db.execSQL("DROP TABLE IF EXISTS soporte");
        onCreate(db);

    }
    public void agregar(String usuario, String contraseña){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMNA_USUARIO, usuario);
        values.put(COLUMNA_CONTRASEÑA, contraseña);

        db.insert(TABLA_NAME, null, values);
        db.close();
    }

    public boolean usuario(String usuario) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {COLUMNA_ID, COLUMNA_USUARIO, COLUMNA_CONTRASEÑA};

        Cursor cursor =
                db.query(TABLA_NAME, projection,
                        "usuario = ?",
                        new String[]{String.valueOf(usuario)},
                        null,
                        null,
                        null,
                        null);
        boolean autenticacion = cursor.moveToFirst();
        db.close();
        return autenticacion;
    }
    public boolean contraseña(String contraseña){

        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {COLUMNA_ID,COLUMNA_USUARIO,COLUMNA_CONTRASEÑA};

        Cursor cursor =
                db.query(TABLA_NAME,projection,
                        "contraseña = ?",
                        new String[] {String.valueOf(contraseña) },
                        null,
                        null,
                        null,
                        null);


        boolean autenticacion = cursor.moveToFirst();
        db.close();
        return autenticacion;

    }
    public boolean confirmacioncedula(String cedula){

        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {"id","cedula","nombres","apellidos","telefono","correo","idevento","idhorario","espectador"};

        Cursor cursor =
                db.query("registros",projection,
                        "cedula = ?",
                        new String[] {String.valueOf(cedula) },
                        null,
                        null,
                        null,
                        null);


        boolean autenticacion = cursor.moveToFirst();

        return autenticacion;

    }
    public String[] consultaCedula(String cedula){

        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {"cedula","nombres","apellidos","telefono","correo","idevento","idhorario","espectador"};

        Cursor fila = db.rawQuery(
                "select *  from registros where cedula=" + cedula, null);

        if(fila.moveToFirst()) {

            String nombre = fila.getString(0);
            String apellido = fila.getString(2);
            String telefono = fila.getString(3);
            String correo = fila.getString(4);
            String idEvento = fila.getString(5);
            String idhorario = fila.getString(6);
            String espectador = fila.getString(7);
            String[] resultado = {nombre, apellido, telefono, correo, idEvento, idhorario, espectador};
            return resultado;
        }
        else return null;

    }
    public String[][] consultareventos(){

        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {"titulo","descripcion","tipodeevento","direccionevento","fecha","hora"};

        Cursor fila = db.rawQuery(
                "select *  from crearevento", null);

        if(fila.moveToFirst()) {

            String[][] resultado = new String[db.rawQuery("select * from crearevento",null).getCount()][8];
            for (int i = 0 ; i< resultado.length ; i++){
                    resultado[i][0]= fila.getString(0);;
                resultado[i][1]= fila.getString(1);
                resultado[i][2]= fila.getString(2);
                resultado[i][3]= fila.getString(3);
                resultado[i][4]= fila.getString(4);
                resultado[i][5]= fila.getString(5);
                resultado[i][6]= fila.getString(6);
                resultado[i][7]= "5";
                fila.moveToNext();

            }

            return resultado;
        }
        else return null;

    }
}


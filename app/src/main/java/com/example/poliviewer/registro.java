package com.example.poliviewer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class registro extends AppCompatActivity {
    private Spinner spinner1;
    private Spinner eventos;
    private Spinner Horario;
    private Spinner Tipo_espectador;

    private EditText cedula, nombres,apellidos,telefono,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_action_bar);



        String [] opciones = {"C.C","T.I","Cedula de extranjeria"};
        String [] evento={"4112 - seguridad informatica","3114 - Big Data","2111 - Lo que el mundo no sabe"};
        String [] horarios={"10:00 AM - 1:00 PM","2:00 PM - 5:00 PM","6:00 PM - 9:00 PM"};
        String [] espectador={"Estudiante","Profesor","Invitado (Externo a la universidad)"};


        spinner1 = (Spinner) findViewById(R.id.spinnerid);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);

        eventos= (Spinner) findViewById(R.id.spinnereventos);
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,evento);
        eventos.setAdapter(adapter1);

        Horario= (Spinner) findViewById(R.id.spinnerhorarios);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,horarios);
        Horario.setAdapter(adapter2);

        Tipo_espectador= (Spinner) findViewById(R.id.spinnerespectador);
        ArrayAdapter<String> adapter3= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,espectador);
        Tipo_espectador.setAdapter(adapter3);

    }
    public void registro(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext());
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
            cedula = (EditText) findViewById(R.id.txt_cedula);
            nombres = (EditText) findViewById(R.id.txt_nombres);
            apellidos = (EditText) findViewById(R.id.txt_apellidos);
            telefono = (EditText) findViewById(R.id.txt_telefono);
            correo = (EditText) findViewById(R.id.txt_correo);
         String c_cedula = cedula.getText().toString();
        String c_nombres =nombres.getText().toString();
        String c_apellidos = apellidos.getText().toString();
        String c_telefono =telefono.getText().toString();
        String c_correo = correo.getText().toString();

        ContentValues values= new ContentValues();

        values.put("cedula", c_cedula);
        values.put("nombres", c_cedula);
        values.put("apellidos", c_cedula);
        values.put("telefono", c_cedula);
        values.put("correo", c_cedula);
        values.put("idevento", 1);
        values.put("idhorario", 1);
        values.put("espectador", "Estudiante");
        BaseDeDatos.insert("registros",null,values);
        Toast.makeText(getApplicationContext(),"Usuario Registrado", Toast.LENGTH_SHORT).show();



        Intent login= new Intent(this, bienvenida.class);
        startActivity(login);
    }
}

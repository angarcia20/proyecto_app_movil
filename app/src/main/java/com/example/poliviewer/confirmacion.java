package com.example.poliviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class confirmacion extends AppCompatActivity {
    TextView Cedula;
    TextView Nombres;
    TextView Apellidos;
    TextView Telefono;
    TextView Correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);
        Bundle extras= getIntent().getExtras();
        String cedula= extras.getString("cedula");
        String nombres =extras.getString("nombres");
        String apellidos =extras.getString("apellidos");
        String telefono =extras.getString("telefono");
        String correo =extras.getString("correo");

        Cedula = (TextView) findViewById(R.id.cedula);
        Cedula.setText(cedula);

        Nombres = (TextView) findViewById(R.id.nombres);
        Nombres.setText(nombres + apellidos);

        Telefono = (TextView) findViewById(R.id.telefono);
        Telefono.setText(telefono);

        Correo = (TextView) findViewById(R.id.correo);
        Correo.setText(correo);
    }
    public void comprobar(View view) {

        Intent comprobacion = new Intent(this, cargaqr.class);

        startActivity(comprobacion);


    }



}

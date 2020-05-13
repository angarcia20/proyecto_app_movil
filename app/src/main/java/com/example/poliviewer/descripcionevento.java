package com.example.poliviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class descripcionevento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcionevento);
        TextView titulo = (TextView) findViewById(R.id.titulo);
        ImageView imagen = (ImageView) findViewById(R.id.imagen);
        TextView descripcion = (TextView) findViewById(R.id.descripcion);
        TextView direccion = (TextView) findViewById(R.id.direccion);

        Bundle extras= getIntent().getExtras();

        titulo.setText(extras.getString("tituloImagen"));
        imagen.setImageResource(extras.getInt("IMG"));
        descripcion.setText(extras.getString("descripcionImagen"));
        direccion.setText(extras.getString("direccionImagen"));





    }
}

package com.example.poliviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class descripcionevento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcionevento);
        TextView titulo = (TextView) findViewById(R.id.titulo);
        ImageView imagen = (ImageView) findViewById(R.id.imagen);
        TextView descripcion = (TextView) findViewById(R.id.descripcionsoporte);
        TextView direccion = (TextView) findViewById(R.id.direccion);
        TextView hora = (TextView) findViewById(R.id.horadelevento);
        TextView tipodeevento = (TextView) findViewById(R.id.tipoevento);
        ImageView imagenmaps = (ImageView) findViewById(R.id.mapsevento);

        Bundle extras= getIntent().getExtras();

        titulo.setText(extras.getString("tituloImagen"));
        imagen.setImageResource(extras.getInt("IMG"));
        descripcion.setText(extras.getString("descripcionImagen"));
        direccion.setText(extras.getString("direccionImagen"));
        hora.setText(extras.getString("horaevento"));
        tipodeevento.setText("Este evento es " + extras.getString("tipodeevento"));


        imagenmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(descripcionevento.this,MapsActivity.class);
                startActivity(intent);

            }
        });





    }
}

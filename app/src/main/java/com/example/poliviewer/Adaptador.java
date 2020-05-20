package com.example.poliviewer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context contexto;
    String[][] datos;
    int[] datosImg;

    public Adaptador(Context contexto,String[][] datos, int[] imagenes){
        this.contexto=contexto;
        this.datos=datos;
        this.datosImg=imagenes;
        inflater= (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista= inflater.inflate(R.layout.elemento_lista,null);
        TextView titulo= (TextView) vista.findViewById(R.id.titulo);
        final TextView descripcion= (TextView) vista.findViewById(R.id.descripcionsoporte);
        TextView direccion= (TextView) vista.findViewById(R.id.direccion);
        ImageView imagen = (ImageView) vista.findViewById(R.id.imagen);
        RatingBar calificacion = (RatingBar) vista.findViewById(R.id.puntuacion);

        titulo.setText(datos[i][1]);
        descripcion.setText(datos[i][2]);
        direccion.setText(datos[i][4]);
        imagen.setImageResource(datosImg[i]);
        calificacion.setProgress(Integer.valueOf(datos[i][7]));

        imagen.setTag(i);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visores = new Intent(contexto,descripcionevento.class);
                visores.putExtra("IMG",datosImg[(Integer)v.getTag()]);
                visores.putExtra("tituloImagen",datos[(Integer)v.getTag()][1]);
                visores.putExtra("descripcionImagen",datos[(Integer)v.getTag()][2]);
                visores.putExtra("direccionImagen",datos[(Integer)v.getTag()][4]);
                visores.putExtra("fecha",datos[(Integer)v.getTag()][5]);
                visores.putExtra("hora",datos[(Integer)v.getTag()][6]);
                visores.putExtra("tipodeevento",datos[(Integer)v.getTag()][3]);
                contexto.startActivity(visores);


            }
        });

        return vista;
    }
    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}

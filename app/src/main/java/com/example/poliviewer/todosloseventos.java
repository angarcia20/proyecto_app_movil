package com.example.poliviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class todosloseventos extends AppCompatActivity {
    ListView lista;
    String[][] datos= {
            {"Big Data","5","Es un término evolutivo que describe cualquier cantidad voluminosa de datos estructurados, semiestructurados y no estructurados que tienen el potencial de ser extraídos para obtener información.","Cra 112 # 13a-24"},
            {"Seguridad informatica","8","La seguridad informática es una disciplina que se encarga de proteger la integridad y la privacidad de la información almacenada en un sistema informático. De todas formas, no existe ninguna técnica que permita asegurar la inviolabilidad de un sistema.","Cll 26 #33-24"},
            {"Lo que el mundo no sabe","4","Una pandemia de influenza es un brote mundial de un nuevo virus de influenza A que es muy diferente de los virus de la influenza estacional A que han circulado entre las personas recientemente o que están en circulación en el momento.","Cra 7 #53-2"},

    };
    int[] datosImg= {R.drawable.bigdata,R.drawable.seguridad,R.drawable.pandemias};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todosloseventos);
        lista = (ListView) findViewById(R.id.lvLista);

        lista.setAdapter(new Adaptador(this,datos,datosImg));
    }
}

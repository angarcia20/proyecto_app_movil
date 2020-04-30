package com.example.poliviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class EscanearQr extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText codigo;
    Button escaner;
    private ZXingScannerView vistaescaner;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toogle;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanear_qr);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }
    public void Escanear(View view){
        vistaescaner = new ZXingScannerView(this);
        vistaescaner.setResultHandler(new zxingscanner());
        setContentView(vistaescaner);
        vistaescaner.startCamera();

    }
    public void verificc(View view){
        Intent intent= new Intent(EscanearQr.this, comprobarCedula.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                Toast.makeText(EscanearQr.this,"Registrar Usuario",Toast.LENGTH_SHORT).show();
                Intent login= new Intent(this, registro.class);
                startActivity(login);
                break;
            case R.id.contact:
                Toast.makeText(EscanearQr.this,"Escanear Qr",Toast.LENGTH_SHORT).show();
                Intent ConfirmarQr= new Intent(this, EscanearQr.class);
                startActivity(ConfirmarQr);
                break;
            case R.id.about:
                Toast.makeText(EscanearQr.this,"Inicio",Toast.LENGTH_SHORT).show();
                Intent bienvenida= new Intent(this, bienvenida.class);
                startActivity(bienvenida);
                break;
            case R.id.logout:
                Toast.makeText(EscanearQr.this,"Sesion Cerrada",Toast.LENGTH_SHORT).show();
                Intent logout= new Intent(this, MainActivity.class);
                startActivity(logout);
                break;

        }
        return false;
    }

    public class zxingscanner implements ZXingScannerView.ResultHandler{

        @Override
        public void handleResult(Result result) {

            String dato = result.getText();
            setContentView(R.layout.activity_escanear_qr);
            vistaescaner.stopCamera();
            codigo = (EditText) findViewById(R.id.edtCodigo);
            codigo.setText(dato);

        }
    }

}

package com.example.poliviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QR extends AppCompatActivity {

    EditText codigo;
    Button escaner;
    private ZXingScannerView vistaescaner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanear_qr);

    }
    public void Escanear(View view){
        vistaescaner = new ZXingScannerView(this);
        vistaescaner.setResultHandler(new QR.zxingscanner());
        setContentView(vistaescaner);
        vistaescaner.startCamera();

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

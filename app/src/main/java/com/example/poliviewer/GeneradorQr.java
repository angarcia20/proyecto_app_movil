package com.example.poliviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;

public class GeneradorQr extends AppCompatActivity {
    TextView Cedula;
    TextView Nombres;
    TextView Apellidos;
    TextView Telefono;
    TextView Correo;
    ImageView imageView;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generador_qr);

        imageView = findViewById(R.id.imageView);

        Bundle extras= getIntent().getExtras();
        String cedula= extras.getString("cedula");
        String nombres =extras.getString("nombres");
        String apellidos =extras.getString("apellidos");
        String telefono =extras.getString("telefono");
        String correo =extras.getString("correo");

        Cedula = (TextView) findViewById(R.id.Cedula);
        Cedula.setText(cedula);

        Nombres = (TextView) findViewById(R.id.Nombres);
        Nombres.setText(nombres + apellidos);

        Telefono = (TextView) findViewById(R.id.Telefono);
        Telefono.setText(telefono);

        Correo = (TextView) findViewById(R.id.Correo);
        Correo.setText(correo);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(cedula, BarcodeFormat.QR_CODE,200,200);
            Bitmap bitmap = Bitmap.createBitmap(200,200,Bitmap.Config.RGB_565);

            for (int x = 0; x<200 ; x++){
                for (int y=0; y<200; y++){
                    bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK : Color.WHITE);
                }
            }
            imageView.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        btn1 = (Button) findViewById(R.id.confirmar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(GeneradorQr.this, cargaqr.class);
                startActivity(intent);
            }
        });

    }

}

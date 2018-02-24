package com.nuevasprofesiones.dam.pmdm.boletin2.ej5bolemultyservicios;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private Button butVideo;
    private EditText edtxtFicheroVideo;
    private Uri archivoMultimedia;
    static final int REQUEST_PHOTO_CAPTURE = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butVideo = (Button)findViewById(R.id.butVideo);
        edtxtFicheroVideo = (EditText)findViewById(R.id.edtxtFicheroVideo);
        butVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dispatchTakePhotoIntent();
            }

        });
    }
    private void dispatchTakePhotoIntent() {
        String ruta = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES).getPath() + File.separator;
        archivoMultimedia = Uri.fromFile(new File(ruta +
                edtxtFicheroVideo.getText().toString() + ".jpg"));
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent,REQUEST_PHOTO_CAPTURE);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO_CAPTURE) {
            if (resultCode == RESULT_OK)
                Toast.makeText(this, "Foto guardada  en:\n" + archivoMultimedia,
                        Toast.LENGTH_LONG).show();
            else if (resultCode == RESULT_CANCELED)
                Toast.makeText(this, "Captura de foto cancelada",
                        Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Captura de foto fallida",
                        Toast.LENGTH_LONG).show();
        }
    }
}

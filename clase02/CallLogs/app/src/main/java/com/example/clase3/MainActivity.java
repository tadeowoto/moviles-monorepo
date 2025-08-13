package com.example.clase3;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestP();
        reedContainer();
    }


    public void requestP () {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG},1000);
        }
    }

    public void reedContainer(){
        //al calls le pasamos la uri del contenedor que queremos consultar
        Uri calls = Uri.parse("content://call_log/calls");
        //obtenemos el content resolver
        ContentResolver cr = getContentResolver();
        //consultamos la tabla
        Cursor c = cr.query(calls,null,null,null,null);
       //declaramos las variables a recorrer
        String number, time;
        //recorremos el cursor para poder ver la consulta

        if(c.getCount() > 0){
            while (c.moveToNext()){
                number=c.getString(c.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
                time=c.getString(c.getColumnIndexOrThrow(CallLog.Calls.DATE));
                Log.d("salida", number);
                Log.d("salida", time);
            }
        }
    }

}
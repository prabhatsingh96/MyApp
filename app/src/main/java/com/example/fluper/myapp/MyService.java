package com.example.fluper.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate(){
        Log.d("text" ,"service started");
        Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int a, int b){

        for(int i = 0; i<15;i++){
            try {
                Thread.sleep(3000);
                Log.d("text", "" + i);
            }catch(Exception e){

            }
        }
        return a;
    }

    @Override
    public void onDestroy(){
        Log.d("text", "service stoped");
        Toast.makeText(this, "service stoped", Toast.LENGTH_SHORT).show();
    }

    @Override

    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

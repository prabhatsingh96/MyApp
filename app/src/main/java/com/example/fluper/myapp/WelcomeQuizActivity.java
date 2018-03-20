package com.example.fluper.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fluper.myapp.appUtil.BaseActivity;

public class WelcomeQuizActivity extends BaseActivity{

    private TextView tvButton;
    private Button baseBtn;
    private FrameLayout fr;
    private Button serviceBtn;
    private Button serviceStopBtn;
    private Intent stopServiceIntent;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_welcome_quiz);

        fr = findViewById(R.id.frame_layout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_welcome_quiz,fr);
        //setContentView(view);

        tvButton = view.findViewById(R.id.btn_start_quiz);
        baseBtn = view.findViewById(R.id.baseBtn);
        serviceBtn = view.findViewById(R.id.start_service);
        serviceStopBtn = view.findViewById(R.id.stop_service);

        stopServiceIntent = new Intent(this, MyService.class);
        serviceIntent = new Intent(this,MyService.class);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startService(serviceIntent);
            }
        });

        serviceStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(stopServiceIntent);
            }
        });

        final Intent baseIntent = new Intent(this, BaseActivity.class);

        baseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(baseIntent);
            }
        });


        final Intent intent = new Intent(this, StartQuizActivity.class);
        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);


            }
        });
    }
  /// Add Question

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Add Question");

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem m){
        int iteamId = m.getItemId();
        switch (iteamId) {
            case 0:
                Intent intent = new Intent(this , AddQuestionActivity.class);
                startActivity(intent);
                break;

        }return super.onOptionsItemSelected(m);

    }

}

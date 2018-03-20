package com.example.fluper.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore.findViewById(R.id.tv_score);
        Intent intent = getIntent();
        long score = intent.getLongExtra("score",0);
        tvScore.setText((int) score);

    }
}

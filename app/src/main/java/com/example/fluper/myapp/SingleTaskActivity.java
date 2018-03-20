package com.example.fluper.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SingleTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

    }

    public void standardBtn(View view) {
        startActivity(new Intent(this, StandardLaunchingActivity.class));
    }

    public void singleTopdBtn(View view) {
        startActivity(new Intent(this, SingleTopActivity.class));
    }

    public void singleInstanceBtn(View view) {
        startActivity(new Intent(this, SingleInstancesActivity.class));
    }
}

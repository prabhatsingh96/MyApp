package com.example.fluper.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SingleTopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);

    }

    public void singleTaskBtn(View view) {
        startActivity(new Intent(this, SingleTaskActivity.class));

        finish();
    }

    public void standardBtn(View view) {
        startActivity(new Intent(this, StandardLaunchingActivity.class));
    }

    public void singleInstanceBtn(View view) {
        startActivity(new Intent(this, SingleInstancesActivity.class));
    }
}

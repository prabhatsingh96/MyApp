package com.example.fluper.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class LaunchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);
    }

    public void standardBtn(View view) {
        startActivity(new Intent(this,StandardLaunchingActivity.class));

    }

    public void singleTaskBtn(View view) {
        startActivity(new Intent(this,SingleTaskActivity.class));
    }

    public void singleTopBtn(View view) {
        startActivity(new Intent(this,SingleTopActivity.class));
    }

    public void singleInstances(View view) {
        startActivity(new Intent(this,SingleInstancesActivity.class));
    }
}

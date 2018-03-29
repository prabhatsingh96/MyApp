package com.example.fluper.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;

public class GmailDetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private ImageView profileImage;
    private Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_gmail_detail);

        name = findViewById (R.id.display_name);
        email = findViewById (R.id.display_email);
        profileImage = findViewById (R.id.display_image);
        logoutBtn = findViewById (R.id.logout_gmail);

        final String dname = getIntent ().getStringExtra ("name");
        String demail = getIntent ().getStringExtra ("email");
        String duri = getIntent ().getStringExtra ("url");

        name.setText (dname);
        email.setText (demail);
        Picasso.with (GmailDetailActivity.this).load (duri).into (profileImage);

       logoutBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent ();
                intent.putExtra ("name",dname);
                setResult (100,intent);
                finish ();
            }
        });


    }
}

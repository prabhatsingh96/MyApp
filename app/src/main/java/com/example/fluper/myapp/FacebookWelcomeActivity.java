package com.example.fluper.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class FacebookWelcomeActivity extends AppCompatActivity {

    private TextView name;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_facebook_welcome);

        name = findViewById (R.id.na);
        image = findViewById (R.id.pr);

        Profile profile = Profile.getCurrentProfile ();
        if (profile != null) {
            name.setText (profile.getName ());
            //email.setText (profile.getId ());

            Picasso.with (FacebookWelcomeActivity.this).load (profile.getProfilePictureUri
                    (400, 400)).into (image);
        }




    }
}

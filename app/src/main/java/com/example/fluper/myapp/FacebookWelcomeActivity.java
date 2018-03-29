package com.example.fluper.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
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



      /*  GraphRequest request = GraphRequest.newMeRequest(
                getIntent ().getStringExtra ("logInResult"),
                new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("Main", response.toString());
                        setProfileToView(object);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender, birthday");
        request.setParameters(parameters);
        request.executeAsync();
*/
    }


   /*  private void setProfileToView(JSONObject jsonObject) {
        try {
            email.setText(jsonObject.getString("email"));
            gender.setText(jsonObject.getString("gender"));
            facebookName.setText(jsonObject.getString("name"));
          ////  profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
           // profilePictureView.setProfileId(jsonObject.getString("id"));
            infoLayout.setVisibility(View.VISIBLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}

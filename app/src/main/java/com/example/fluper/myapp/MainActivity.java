package com.example.fluper.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fluper.myapp.appUtil.DbAdapter;
import com.example.fluper.myapp.appUtil.UserDetail;
import com.example.fluper.myapp.frangments.LogInFragments;
import com.example.fluper.myapp.frangments.SignUpFragments;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String TAG = "TEST";
    private Button signIn;
    private LoginButton facebookLogInBtn;
    private DbAdapter adapter;
    private Button signUp;
    private SignInButton googleLogInBtn;
    private CallbackManager callbackManager;
   /* private ImageView profilePictureView;
    private LinearLayout infoLayout;
    private TextView email;
    private TextView gender;
    private TextView facebookName;
    private Bitmap bitmap;*/
    private static final String EMAIL = "email";
    private GoogleSignInClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);






        adapter = new DbAdapter(this);

        retrieveDataFromDatabase();

        signUp = findViewById(R.id.signUp_btn);
        signIn = findViewById(R.id.sl_btn);
        facebookLogInBtn = findViewById(R.id.facebook_log_in_btn);
        googleLogInBtn = findViewById (R.id.signing_with_google);
        //RelativeLayout rl = findViewById (R.id.in);



      /*  email = rl.findViewById(R.id.email);
        facebookName = (TextView)rl.findViewById(R.id.name);
        gender = (TextView)rl.findViewById(R.id.gender);
        infoLayout = (LinearLayout)rl.findViewById(R.id.layout_info);
        profilePictureView = rl.findViewById(R.id.image);
*/

        android.support.v4.app.FragmentManager  fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.layout_container , new LogInFragments());
        ft.commit();
        signIn.setVisibility(View.GONE);
        signUp.setVisibility(View.VISIBLE);

        facebookLogInBtn.setReadPermissions(Arrays.asList(EMAIL));
        callbackManager = CallbackManager.Factory.create();

        facebookLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInToFacebook();
            }
        });

        googleLogInBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                signInToGoogle();
            }
        });


    }

    private void signInToGoogle() {


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        //updateUI(account);
        Intent signInIntent = mGoogleApiClient.getSignInIntent();
        startActivityForResult(signInIntent, 10);

           }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String name = account.getDisplayName ();
            String email = account.getEmail ();
            String url = account.getPhotoUrl ().toString ().trim ();
            //Log.d ("test","photo url : "+url);
            Intent gmailIntent = new Intent(this,GmailDetailActivity.class);
            gmailIntent.putExtra ("name",name);
            gmailIntent.putExtra ("email",email);
            gmailIntent.putExtra ("url",url);
            startActivityForResult (gmailIntent,100);


            Toast.makeText (this, "LogIn SuccessFully", Toast.LENGTH_SHORT).show ();
            // Signed in successfully, show authenticated UI.
           // updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
           // updateUI(null);
        }
    }









    //Log in to facebook
    public void logInToFacebook() {
        // faceBookLogInBtn = findViewById(R.id.log_in_with_linkdin);
        //facebookLogInBtn.setReadPermissions(Arrays.asList(EMAIL));

        LoginManager.getInstance ().logInWithReadPermissions (this,
                Arrays.asList (EMAIL));


        //callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Toast.makeText (MainActivity.this, "log in successfully", Toast.LENGTH_SHORT).show ();

                startActivity (new Intent (MainActivity.this,
                        FacebookWelcomeActivity.class).putExtra ("logInResult",loginResult.getAccessToken ()));


                // Toast.makeText (MainActivity.this, "LogIn Success", Toast.LENGTH_SHORT).show ();
              /*  GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
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
            }*/
            }


            @Override
            public void onCancel() {

                Toast.makeText(MainActivity.this, "LogIn Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error)
            {
                Toast.makeText(MainActivity.this, "error"+error, Toast.LENGTH_SHORT).show();
            }
        });

    }


   /* private void setProfileToView(JSONObject jsonObject) {
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 10) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        if(requestCode == 100){

            signOut ();
        }
    }


    private void signOut() {
        mGoogleApiClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void> () {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText (MainActivity.this, "you are logOut",
                                Toast.LENGTH_SHORT).show ();
                    }
                });
    }





    private void retrieveDataFromDatabase() {

        Log.d(TAG, "Retrieving data from database ...");
        ArrayList<UserDetail> useList = adapter.showData();
        for (UserDetail detail : useList){
            Log.d("TEST","Email " + detail.getEmail());
            Log.d("TEST", "Password "+ detail.getPassword());
        }
    }

    public void signUpBtn(View view){
        android.support.v4.app.FragmentManager  fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_container , new SignUpFragments());
        ft.commit();
        signUp.setVisibility(View.GONE);
        signIn.setVisibility(View.VISIBLE);
    }

    public void signInBtn(View view){
        android.support.v4.app.FragmentManager  fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_container , new LogInFragments());
        ft.commit();
        signIn.setVisibility(View.GONE);
        signUp.setVisibility(View.VISIBLE);

    }

    public void launchingBtn(View view) {
        startActivity(new Intent(this, LaunchingActivity.class));
    }

}

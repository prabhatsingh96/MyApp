package com.example.fluper.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fluper.myapp.appUtil.DbAdapter;
import com.example.fluper.myapp.appUtil.UserDetail;
import com.example.fluper.myapp.frangments.LogInFragments;
import com.example.fluper.myapp.frangments.SignUpFragments;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String TAG = "TEST";
    private Button signIn;
    private DbAdapter adapter;
    private Button signUp;
    private  Button facebookLogInBtn;
    private  Button googleLogInBtn;
    private CallbackManager callbackManager;

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

        android.support.v4.app.FragmentManager  fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.layout_container , new LogInFragments());
        ft.commit();
        signIn.setVisibility(View.GONE);
        signUp.setVisibility(View.VISIBLE);

        facebookLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInToFacebook();
            }
        });


    }



    //Log in to facebook
    public void logInToFacebook(){
        // faceBookLogInBtn = findViewById(R.id.log_in_with_linkdin);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                Toast.makeText(MainActivity.this, "LogIn Success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {

                Toast.makeText(MainActivity.this, "LogIn Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
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

   /* public void signingwithgoogle(View view) {

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
          mGoogleSignInClient = GoogleSignIn.getClient (this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        signIn();

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
*/
}

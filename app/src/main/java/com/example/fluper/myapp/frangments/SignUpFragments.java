package com.example.fluper.myapp.frangments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fluper.myapp.R;
import com.example.fluper.myapp.appUtil.DbAdapter;
import com.example.fluper.myapp.appUtil.UserDetail;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragments extends Fragment {


    private String TAG = "TEST";
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etContact;
    private Button signUpButton;
    private View view ;
    private DbAdapter adapter ;

    public SignUpFragments() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DbAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_sign_up_frangments, container, false);
        gettingId();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




               insertData();

            }
        });

        return view;
    }

    private void insertData() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        Log.d(TAG, "Inserting Data to Database");
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String contact = etContact.getText().toString().trim();
        if (email.matches(emailPattern)) {
            boolean res = adapter.insertData(name, email, password, contact);
            if (res) Toast.makeText(getContext(), "Data Inserted ", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "Error while inserting data", Toast.LENGTH_SHORT).show();
        }
    }

    // Getting Id
    public void gettingId(){
        etName = view.findViewById(R.id.et_name);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        etContact = view.findViewById(R.id.et_contact);
        signUpButton = view.findViewById(R.id.sign_up_btn);
    }

}

package com.example.fluper.myapp.frangments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fluper.myapp.R;
import com.example.fluper.myapp.WelcomeQuizActivity;
import com.example.fluper.myapp.appUtil.DbAdapter;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragments extends Fragment {

   private EditText etEmail;
   private  EditText etPassword ;
   private Button logInBtn;
   ImageView imageView;
   DbAdapter adapter;
    public LogInFragments() {
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
         //imageView = imageView.findViewById(R.id.image_view);
      //  Picasso.with(getContext()).load().into(imageView);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in_fragments, container, false);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        logInBtn = view.findViewById(R.id.log_in_btn);

        logInBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                //if (email.matches(emailPattern)) {

               // Log.d("tag" , email);
               // Log.d("tag" , password);
                    if (email.matches(emailPattern) && adapter.validate(email, password)) {

                       startActivity(new Intent(getActivity(), WelcomeQuizActivity.class));

                      //  Toast.makeText(getContext(), "Welcome :" + email, Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getContext(), "Please Enter valid email Id", Toast.LENGTH_SHORT).show();
                //}
            }
        });
        return view;
    }


}

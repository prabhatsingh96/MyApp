package com.example.fluper.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fluper.myapp.appUtil.DbTest;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText ques;
    private EditText ansOne;
    private EditText ansTwo;
    private EditText ansThree;
    private EditText ansFour;
    private EditText rightAns;
    private Button saveButton;
    DbTest dbTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        ques = findViewById(R.id.ques);
        ansOne = findViewById(R.id.ans_one);
        ansTwo = findViewById(R.id.ans_two);
        ansThree = findViewById(R.id.ans_three);
        ansFour = findViewById(R.id.ans_four);
        rightAns = findViewById(R.id.ans_right);
        saveButton = findViewById(R.id.save);


        dbTest = new DbTest(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String question = ques.getText().toString().trim();
                String answerOne = ansOne.getText().toString().trim();
                String answerTwo = ansTwo.getText().toString().trim();
                String answerThree = ansThree.getText().toString().trim();
                String answerFour = ansFour.getText().toString().trim();
                String rightAnswer = rightAns.getText().toString().trim();


                boolean b = dbTest.insertQuestion(question,answerOne,answerTwo,answerThree,answerFour,rightAnswer);
                if(b) {

                    Toast.makeText(AddQuestionActivity.this, "question saved", Toast.LENGTH_SHORT).show();
                    ques.setText("");
                    ansOne.setText("");
                    ansTwo.setText("");
                    ansThree.setText("");
                    ansFour.setText("");
                    rightAns.setText("");
                }
            }
        });

    }


}

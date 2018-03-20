package com.example.fluper.myapp;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fluper.myapp.appUtil.DbAdapter;
import com.example.fluper.myapp.appUtil.DbTest;
import com.example.fluper.myapp.appUtil.QuesAnswer;

public class StartQuizActivity extends AppCompatActivity {

    private EditText ques;
    private RadioButton radioButton;
    private RadioButton ansOne;
    private RadioButton ansTwo;
    private RadioButton ansThree;
    private RadioButton ansFour;
    private Button nextBtn;
    private Button submitBtn;
    private RadioGroup rg;
    private QuesAnswer qs1;
    private DbTest dbTest;
    QuesAnswer qs;
    QuesAnswer qsq;
    long score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        rg = (RadioGroup) findViewById(R.id.radio_grp);

        gettingId();
        submitBtn.setVisibility(View.GONE);
        DbAdapter dbAdapter = new DbAdapter(this);
       // dbAdapter.insertQuestion("what is your name", "ans1","ans2","ans3", "ans4", "ans3");
        dbTest = new DbTest(this);
        final Intent submitIntent = new Intent(this,ResultActivity.class);

            dbTest.retriveData();
            qs = dbTest.question();
        ques.setText(qs.getQues());
        ansOne.setText(qs.getAnsOne());
        ansTwo.setText(qs.getAnsTwo());
        ansThree.setText(qs.getAnsThree());
        ansFour.setText(qs.getAnsFour());
            //ACtion on next button

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qs = dbTest.question();
                if(qs != null) {
                    ques.setText(qs.getQues());
                    ansOne.setText(qs.getAnsOne());
                    ansTwo.setText(qs.getAnsTwo());
                    ansThree.setText(qs.getAnsThree());
                    ansFour.setText(qs.getAnsFour());
                } else{
                    nextBtn.setVisibility(View.GONE);
                }
            }
        });




        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                radioButton = (RadioButton) findViewById(checkedId);
                qsq = dbTest.rightAnswerComparision();
                String value = radioButton.getText().toString();
                if(qsq !=null) {
                    if (value.equals(qsq.getRightAns().toString())) {

                        score++;
                        Toast.makeText(StartQuizActivity.this, "" + score, Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    nextBtn.setVisibility(View.GONE);
                    submitBtn.setVisibility(View.VISIBLE);

                    submitBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                         submitIntent.putExtra("score" , score);
                         startActivity(submitIntent);
                        }
                    });
                }
            }
                                      }
        );




    }


    public void gettingId(){
        ques = findViewById(R.id.et_question);
        ansOne = findViewById(R.id.radio_btn_answer_one);
        ansTwo = findViewById(R.id.radio_btn_answer_two);
        ansThree = findViewById(R.id.radio_btn_answer_three);
        ansFour = findViewById(R.id.radio_btn_answer_four);
        nextBtn = findViewById(R.id.btn_next);
        submitBtn = findViewById(R.id.btn_submit);
    }
}

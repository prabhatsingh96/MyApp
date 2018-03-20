package com.example.fluper.myapp.appUtil;

/**
 * Created by fluper on 10/2/18.
 */

public class QuesAnswer {

    private int quesId;
    private String ques;
    private String ansOne;
    private String ansTwo;
    private String ansThree;
    private String ansFour;
    private String rightAns;

    public QuesAnswer(int quesId, String ques, String ansOne, String ansTwo, String ansThree, String ansFour, String rightAns) {
        this.quesId = quesId;
        this.ques = ques;
        this.ansOne = ansOne;
        this.ansTwo = ansTwo;
        this.ansThree = ansThree;
        this.ansFour = ansFour;
        this.rightAns = rightAns;
    }

    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAnsOne() {
        return ansOne;
    }

    public void setAnsOne(String ansOne) {
        this.ansOne = ansOne;
    }

    public String getAnsTwo() {
        return ansTwo;
    }

    public void setAnsTwo(String ansTwo) {
        this.ansTwo = ansTwo;
    }

    public String getAnsThree() {
        return ansThree;
    }

    public void setAnsThree(String ansThree) {
        this.ansThree = ansThree;
    }

    public String getAnsFour() {
        return ansFour;
    }

    public void setAnsFour(String ansFour) {
        this.ansFour = ansFour;
    }

    public String getRightAns() {
        return rightAns;
    }

    public void setRightAns(String rightAns) {
        this.rightAns = rightAns;
    }
}
package com.example.little_chemist.Quiz;

import android.content.Context;
import android.util.Log;

import alirezat775.lib.carouselview.CarouselModel;
import com.example.little_chemist.R;

public final class model extends CarouselModel {
    private int id;
    private Context context;
    private String question, op1, op2, op3, op4, correctop;


//get the question and answers from here
    public final String getId() {
        //int test1 = context.getResources().getIdentifier("C1L4S2", "string", context.getPackageName());
        String test2 = context.getResources().getString(R.string.C1L1S1);
        Log.d("000000000000000000", test2);
        return "Question "+test2;
    }

    public model(Context context, int id, String question, String op1, String op2, String op3, String op4, String correctop) {
        this.context = context;
        this.id = id;
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.correctop = correctop;
    }

    public String getcho1(){
        String cho1 = "Text1";
        return cho1;
    }

    public String getcho2(){
        String cho2 = "Text2";
        return cho2;
    }

    public String getcho3(){
        String cho3 = "Text3";
        return cho3;
    }

    public String getcho4(){
        String cho4 = "Text4";
        return cho4;
    }

    public String getQuestion() {
        return question;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getOp3() {
        return op3;
    }

    public String getOp4() {
        return op4;
    }

    public String getCorrectop() {
        return correctop;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public void setCorrectop(String correctop) {
        this.correctop = correctop;
    }
}
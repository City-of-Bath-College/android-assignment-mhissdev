package com.example.mhissdev.com.myapplication;

/**
 * Created by Mark on 03/10/2015.
 */
public class QuestionObject {

    /* Member variables */
    private String question;
    private Boolean answer;
    private int picture;

    /* Constructor */
    public QuestionObject(String question, boolean answer, int picture){
        /* Assign vars */
        this.question = question;
        this.answer = answer;
        this.picture = picture;
    }

    /* Public methods */
    public String getQuestion(){
        return this.question;
    }

    public Boolean getAnswer(){
        return this.answer;
    }

    public int getPicure(){
        return this.picture;
    }
}

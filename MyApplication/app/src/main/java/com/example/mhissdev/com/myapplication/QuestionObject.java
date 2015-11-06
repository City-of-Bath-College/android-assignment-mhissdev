package com.example.mhissdev.com.myapplication;

/**
 * Created by Mark on 03/10/2015.
 */
public class QuestionObject {

    /* Member variables */
    private String question;
    private Boolean answer;
    private String woeid;

    /* Constructor */
    public QuestionObject(String question, Boolean answer, String woeid){
        /* Assign vars */
        this.question = question;
        this.answer = answer;
        this.woeid = woeid;
    }

    /* Public methods */
    public String getQuestion(){
        return this.question;
    }

    public Boolean getAnswer(){
        return this.answer;
    }

    public String getImageURL(){
        return this.woeid;
    }
}

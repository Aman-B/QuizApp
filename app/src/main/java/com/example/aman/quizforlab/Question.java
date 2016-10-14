package com.example.aman.quizforlab;

import java.io.Serializable;

/**
 * Created by Aman on 10/9/2016.
 */

public class Question implements Serializable{

    private int ID;



    private String QUESTION;
    private String OPT_A;
    private String OPT_B;
    private String OPT_C;
    private String OPT_D;
    private String ANSWER;

    public Question()

    {

        QUESTION="";
        OPT_A=" ";
        OPT_B="";
        OPT_C="";
        OPT_D="";
        ANSWER="";

    }
    //constructer for setting values
    public Question(String QUESTION,String OPT_A,String OPT_B,String OPT_C,String OPT_D,String ANSWER)
    {

        this.QUESTION=QUESTION;
        this.OPT_A=OPT_A;
        this.OPT_B=OPT_B;
        this.OPT_C=OPT_C;
        this.OPT_D=OPT_D;
        this.ANSWER=ANSWER;
    }

    //getter and setters

    public int getID()
    {
        return ID;
    }
    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }
    public String getOPT_A() {
        return OPT_A;
    }

    public void setOPT_A(String OPT_A) {
        this.OPT_A = OPT_A;
    }


    public String getOPT_B() {
        return OPT_B;
    }

    public void setOPT_B(String OPT_B) {
        this.OPT_B = OPT_B;
    }

    public String getOPT_C() {
        return OPT_C;
    }

    public void setOPT_C(String OPT_C) {
        this.OPT_C = OPT_C;
    }

    public String getOPT_D() {
        return OPT_D;
    }

    public void setOPT_D(String OPT_D) {
        this.OPT_D = OPT_D;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }


}

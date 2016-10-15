package com.example.aman.quizforlab;

import java.util.ArrayList;

/**
 * Created by Aman on 10/16/2016.
 */

public class AnswerHandler {

    public void initAnswers(ArrayList<Integer> answers,int questions_size)
    {
        for(int i=0;i<questions_size;i++)
        {
            answers.add(-1);
        }

    }



}

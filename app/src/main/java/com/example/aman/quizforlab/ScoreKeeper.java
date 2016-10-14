package com.example.aman.quizforlab;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Aman on 10/9/2016.
 */

public class ScoreKeeper extends Application  {


    private int mScore=0;



    private ArrayList<Integer> selectedId=new ArrayList<>();



    public void fillListWithDummySelectedIds()
    {
        for (int i=0;i<5;i++)
        {
            selectedId.add(-1);
        }
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public int getSelectedId(int position) {
        if(selectedId.isEmpty())
        {
            return -1;


        }
        return selectedId.get(position);

    }

    public void setSelectedId(int position, int value) {
        selectedId.set(position,value);
    }




}

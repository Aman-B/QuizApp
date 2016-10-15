package com.example.aman.quizforlab;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Aman on 10/9/2016.
 */

public class SelectedIdKeeper implements  Serializable {






    private ArrayList<Integer> selectedId=new ArrayList<>();



    public void fillListWithDummySelectedIds()
    {
        for (int i=0;i<5;i++)
        {
            selectedId.add(-1);
        }
    }



    public int getSelectedId(int position) {
        if(selectedId.isEmpty())
        {
            return -1;


        }
        return selectedId.get(position);

    }

    public void setSelectedId(int question_number, int selectedId) {
        this.selectedId.set(question_number,selectedId);
    }




}

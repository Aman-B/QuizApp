package com.example.aman.quizforlab;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.aman.quizforlab.fragments.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aman on 10/15/2016.
 */

public class FragmentHandler {


    public void createFirstFragment(View mFragmentContainer, Bundle msavedInstanceState,
                                    FragmentManager supportFragmentManager,
                                    SelectedIdKeeper mSelectedIdKeeper, int containerID,
                                    int question_number, List<Question> questions, ArrayList<Integer> answers)

        {
            if ((mFragmentContainer) != null)
            {

                // However, if we're being restored from a previous state,
                // then we don't need to do anything and should return or else
                // we could end up with overlapping fragments.
                if (msavedInstanceState != null) {
                    return;
                }

                // Create a new Fragment to be placed in the activity layout

                QuestionFragment firstFragment = new QuestionFragment();

                // In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments
                Bundle bundle = new Bundle();
                bundle.putInt("question_number", question_number);
                bundle.putIntegerArrayList("answers",answers);
                bundle.putSerializable("selectedIdKeeper_object",mSelectedIdKeeper);
                bundle.putSerializable("question_object", questions.get(0));
                firstFragment.setArguments(bundle);


                // Add the fragment to the 'fragment_container' FrameLayout
                        supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .add(containerID,firstFragment).commit();

        }

    }

    public void replaceFragment(FragmentManager supportFragmentManager,
                                int containerID,
                                SelectedIdKeeper mSelectedIdKeeper, int question_number,
                                Question question, ArrayList<Integer> answers)
    {
        // Create fragment and give it an argument specifying the article it should show
        QuestionFragment newFragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt("question_number",question_number);
        args.putIntegerArrayList("answers",answers);
        args.putSerializable("selectedIdKeeper_object",mSelectedIdKeeper);
        args.putSerializable("question_object",question);
        newFragment.setArguments(args);

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(containerID, newFragment);
        // transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }
}

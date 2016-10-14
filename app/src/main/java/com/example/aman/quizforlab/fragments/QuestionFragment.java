package com.example.aman.quizforlab.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aman.quizforlab.MainActivity;
import com.example.aman.quizforlab.Question;
import com.example.aman.quizforlab.R;

import static com.example.aman.quizforlab.R.id.radioGroup;

/**
 * Created by Aman on 10/9/2016.
 */

public class QuestionFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    onQuestionChangeRequestedListener mCallback;

    Question question;
    TextView tv_text;
    RadioGroup mRadioGroup;

    int mscore;
    int question_number=-1;
    int got_checkedId;

    // Container Activity must implement this interface
    public interface onQuestionChangeRequestedListener {
         void onQuestionChangeRequested(Question question_number);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onQuestionChangeRequestedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null)
        {
            question=(Question) getArguments().getSerializable("question_object");
            question_number=getArguments().getInt("question_number");
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_text=(TextView)getActivity().findViewById(R.id.textView);
        mRadioGroup=(RadioGroup)getActivity().findViewById(radioGroup);

        tv_text.setText(String.valueOf(question.getQUESTION()));

        ((RadioButton) mRadioGroup.getChildAt(0)).setText(question.getOPT_A());

        ((RadioButton) mRadioGroup.getChildAt(1)).setText(question.getOPT_B());

        ((RadioButton) mRadioGroup.getChildAt(2)).setText(question.getOPT_C());

        ((RadioButton) mRadioGroup.getChildAt(3)).setText(question.getOPT_D());

        //listener for radio group
        mRadioGroup.setOnCheckedChangeListener(this);

   //     mscore=((MainActivity)getActivity()).getScore();
        if(question_number!=-1)
        {
            if((got_checkedId=((MainActivity)getActivity()).getSelectedId(question_number))!=-1)
            {
                Log.i("Question number : ", " "+question_number);
                mRadioGroup.check(got_checkedId);
            }
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        String answer=null;




        RadioButton radioButton = (RadioButton) getActivity().findViewById(checkedId);

        ((MainActivity)getActivity()).setSelectedId(checkedId);



        if(question.getANSWER().equals(radioButton.getText()))
        {
            answer= "correct answer";
          //  mscore++;
            Log.i("Putting 1 in : ", " "+question_number);
            ((MainActivity)getActivity()).setAnswers(question_number,1);

            //Log.i("Score :"," "+mscore);


        }
        else
        {
            answer="wrong answer";
            ((MainActivity)getActivity()).setAnswers(question_number,0);
        }
        Toast.makeText(getContext(),"Answer? "+answer, Toast.LENGTH_SHORT).show();


    }


}

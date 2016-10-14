package com.example.aman.quizforlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity{

    TextView textView;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();
        setContentView(R.layout.activity_result);
       // setupWindowAnimations();


        score=getIntent().getIntExtra("score",0);

        textView=(TextView) findViewById(R.id.textView2);

        textView.setText("Score : "+ String.valueOf(score));
        this.getSupportActionBar().show();
        //TODO :put functionality for up button
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupWindowAnimations() {
       /* Transition trans = new Explode();
        getWindow().setEnterTransition(trans);*/

       /* Transition returnTrans1 = new Slide();
        returnTrans1.setDuration(500);
        ((Slide)returnTrans1).setSlideEdge(Gravity.RIGHT);
        getWindow().setEnterTransition(returnTrans1);*/


        /*Transition returnTrans = new Slide();
        returnTrans.setDuration(500);
        ((Slide)returnTrans).setSlideEdge(Gravity.RIGHT);
        getWindow().setReturnTransition(returnTrans);*/
        //getWindow().setAllowReturnTransitionOverlap(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.pull_in_right,R.anim.push_out_left);
    }
}

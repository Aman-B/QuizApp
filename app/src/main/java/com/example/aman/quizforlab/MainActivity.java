package com.example.aman.quizforlab;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.aman.quizforlab.database.DBContract;
import com.example.aman.quizforlab.database.DBHelper;
import com.example.aman.quizforlab.fragments.QuestionFragment;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener,QuestionFragment.onQuestionChangeRequestedListener,CallbackForUI {



    int question_number=0;
    Button btn_nextfragment,btn_preivousfragment;
    List<Question> questions;
    int mscore=0;
    int position,selectedId;
    SelectedIdKeeper mSelectedIdKeeper;

    ArrayList<Integer> answers= new ArrayList<>();
    Bundle msavedInstanceState;
    AsyncTask asyncTask;
    FirebaseDb firebaseDb;

    FirebaseDatabase mFirebaseDatabase;
    GenericTypeIndicator<List<Question>> quesListGenericTypeIndicator;
    List <Question>questionArrayList;
    ProgressDialog ringProgressDialog;
    static boolean calledAlready = false;
    View mFragmentContainer;
    FragmentManager supportFragmentManager;
    FragmentHandler mFragmentHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msavedInstanceState = savedInstanceState;
        setupAnimationWindows();

        setContentView(R.layout.activity_main);
        ringProgressDialog= new ProgressDialog(MainActivity.this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.show();
        this.getSupportActionBar().show();

        //To get the selected Id of radio button and retain it when user comes back to the fragment.
        mSelectedIdKeeper = new SelectedIdKeeper();
        mSelectedIdKeeper.fillListWithDummySelectedIds();

        //For fragment handling
        supportFragmentManager= getSupportFragmentManager();
        mFragmentHandler= new FragmentHandler();



        initUIComponents();

       /* ringProgressDialog.setMessage("Loading Questions...");
        ringProgressDialog.setTitle("Loading title...");
        ringProgressDialog.show();
        ringProgressDialog.setCancelable(true);*/
        setButtonVisbility(View.GONE);

        initData();




    }

    private void initUIComponents() {

        mFragmentContainer=(View) findViewById(R.id.fragment_container);

        btn_nextfragment = (Button) findViewById(R.id.button_next_fragment);
        btn_preivousfragment = (Button) findViewById(R.id.button_previous_fragment);

        btn_nextfragment.setOnClickListener(this);
        btn_preivousfragment.setOnClickListener(this);
    }

    public void initData()
    {

        FirebaseDb firebaseDb = new FirebaseDb(this);
        firebaseDb.init();
       // showUI(questionArrayList);
        /*quesListGenericTypeIndicator
                =new GenericTypeIndicator<List<Question>>() {};

        if(!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready=true;
        }
        mFirebaseDatabase= FirebaseDatabase.getInstance();

        DatabaseReference mDatabaseReference= mFirebaseDatabase.getReference();

        DatabaseReference mQuestionReference=mDatabaseReference.child("data");

        mQuestionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //hide buttons




                questionArrayList = dataSnapshot.getValue(quesListGenericTypeIndicator);
                Log.i("Mtag", ""+questionArrayList);
                Log.i("Mytag", " "+questionArrayList.get(0).getQUESTION());
                //return list here

                showUI();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("mTag", "Failed to read value.", databaseError.toException());
                showUI();

            }
        });*/

//        Log.i("Mytag", " "+questionArrayList.get(0).getQUESTION());
       /* return questionArrayList;*/
    }

    private void setButtonVisbility(int buttonVisbility) {
        btn_nextfragment.setVisibility(buttonVisbility);
        btn_nextfragment.setVisibility(buttonVisbility);


    }


    private void createFirstFragment() {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout

        Log.i("selectedidkeeper as : "," "+mSelectedIdKeeper);
        mFragmentHandler.createFirstFragment(mFragmentContainer,msavedInstanceState,supportFragmentManager,
                mSelectedIdKeeper,
                R.id.fragment_container,question_number,questions,answers);

    }






    private void setupAnimationWindows() {
        //TODO: Do something about this later.
       /* Transition exitTrans = new Explode();
        getWindow().setExitTransition(exitTrans);*/
       /* Transition reenterTrans1 = new Slide();
        reenterTrans1.setDuration(500);
        ((Slide)reenterTrans1).setSlideEdge(Gravity.LEFT);
        getWindow().setReturnTransition(reenterTrans1);
        getWindow().setAllowReturnTransitionOverlap(true);*/

      /*  Transition reenterTrans = new Slide();
        reenterTrans.setDuration(500);
        ((Slide)reenterTrans).setSlideEdge(Gravity.LEFT);
        getWindow().setExitTransition(reenterTrans);*/


    }

    private void initAnswers() {
        //question size used here
       AnswerHandler answerHandler= new AnswerHandler();
        answerHandler.initAnswers(answers,questions.size());
        createFirstFragment();
    }

    public  void setAnswers(int index,int value)
    {
       answers.set(index,value);
        Log.i("Answers ", ""+answers);
    }
    public  ArrayList getAnswers()
    {

        return answers;
    }





    @Override
    public void onClick(View view) {
        //increment question number
        int id=view.getId();
        switch (id)
        {
            case R.id.button_next_fragment:
                if(question_number==4)
                {
                    getFinalScore();

                    //  Toast.makeText(getApplicationContext(),"Nothing to go back to! Score :"+" "+mscore,Toast.LENGTH_SHORT).show();
                    Log.i("Score : ", ""+mscore);
                    Intent i = new Intent(this,ResultActivity.class);
                    //for smooth tranisition

                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                    i.putExtra("score",mscore);
                   /* Bundle bndlanimation =
                            ActivityOptions.makeCustomAnimation(getApplicationContext(), this).toBundle();*/
                    this.startActivity(i,bundle);
                    // overridePendingTransition(R.anim.pull_in_left,R.anim.push_out_right);
                }
                else {
                    onQuestionChangeRequested(questions.get(++question_number));
                }
                break;

            case R.id.button_previous_fragment:
                if(question_number==0)
                {
                    getFinalScore();

                  //  Toast.makeText(getApplicationContext(),"Nothing to go back to! Score :"+" "+mscore,Toast.LENGTH_SHORT).show();
                    Log.i("Score : ", ""+mscore);
                    Intent i = new Intent(this,ResultActivity.class);
                    //for smooth tranisition

                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                    i.putExtra("score",mscore);
                   /* Bundle bndlanimation =
                            ActivityOptions.makeCustomAnimation(getApplicationContext(), this).toBundle();*/
                    this.startActivity(i,bundle);
                   // overridePendingTransition(R.anim.pull_in_left,R.anim.push_out_right);



                }
                else {
                    onQuestionChangeRequested(questions.get(--question_number));
                }
                break;
        }

    }

    private void getFinalScore() {
        mscore=0;
        for(int i : answers)
        {
            if(i==1)
            {
                //Log.i("here : ",""+i);
                mscore++;
            }
        }

    }

    @Override
    public void onQuestionChangeRequested(Question question) {
        mFragmentHandler.replaceFragment(supportFragmentManager,R.id.fragment_container,
                mSelectedIdKeeper,
                question_number,question,answers);

    }

    private List<Question> getDataFromDatabase() {

        List<Question> questionList=new ArrayList<Question>();

        DBHelper mDbHelper= new DBHelper(getApplicationContext());
        SQLiteDatabase mSqLiteDatabase= mDbHelper.getReadableDatabase();

        Cursor mCursor= mSqLiteDatabase.rawQuery("select * from "+ DBContract.DBEntry.TABLE_NAME,null);

        if(mCursor.moveToFirst())
        {
            do
            {
                Question mQuestion= new Question();
                mQuestion.setQUESTION(mCursor.getString(1));
                mQuestion.setOPT_A(mCursor.getString(2));
                mQuestion.setOPT_B(mCursor.getString(3));
                mQuestion.setOPT_C(mCursor.getString(4));
                mQuestion.setOPT_D(mCursor.getString(5));
                mQuestion.setANSWER(mCursor.getString(6));
                questionList.add(mQuestion);
            }while(mCursor.moveToNext());
        }

        return questionList;


    }

    @Override
    public void showUI(List<Question> questionArrayList) {
        if(questionArrayList!=null) {
            questions = questionArrayList;
        }
        else
        {
            //In case firebase doesn't work, show data from db.
            Log.i("Data from ", "Db");
            questions=getDataFromDatabase();
        }
        Log.i("Questions ", " : " + questions);
        for (Question q : questions) {
            Log.i("Qdetails : ", "" + q.getQUESTION().toString());
        }
        ringProgressDialog.dismiss();
        //Got data. Show buttons.
        setButtonVisbility(View.VISIBLE);


        initAnswers();
    }
}

//TODO: 1. Clean the code as much as possible. Remove all unnecessary stuff. Like Db and extra classes etc.
//TODO: 2. Make a class for answer init and other stuff.
//TODO: 3. Give proper functions to buttons.
//TODO: 4. Work on UI. Make it beautiful.
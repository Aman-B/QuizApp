package com.example.aman.quizforlab;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aman.quizforlab.database.DBContract;
import com.example.aman.quizforlab.database.DBHelper;
import com.example.aman.quizforlab.fragments.QuestionFragment;
import com.example.aman.quizforlab.fragments.ResultFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener,QuestionFragment.onQuestionChangeRequestedListener {


    
    int question_number=0;
    Button btn_nextfragment,btn_preivousfragment;
    List<Question> questions;
    int mscore=0;
    int position,selectedId;
    ScoreKeeper mScoreKeeper;

    ArrayList<Integer> answers= new ArrayList<>();
    Bundle msavedInstanceState;
    AsyncTask asyncTask;
    FirebaseDb firebaseDb;

    FirebaseDatabase mFirebaseDatabase;
    GenericTypeIndicator<List<Question>> quesListGenericTypeIndicator;
    List <Question>questionArrayList;
    ProgressDialog ringProgressDialog;
    static boolean calledAlready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msavedInstanceState = savedInstanceState;
        setupAnimationWindows();
        // overridePendingTransition(R.transition.fadein,R.transition.fadeout);

        setContentView(R.layout.activity_main);
        this.getSupportActionBar().show();
        /*setupAnimationWindows();*/
        mScoreKeeper = new ScoreKeeper();
        mScoreKeeper.fillListWithDummySelectedIds();
        // mscore=mScoreKeeper.getmScore();

        //getApplicationContext().deleteDatabase("Quiz.db");
        btn_nextfragment = (Button) findViewById(R.id.button_next_fragment);
        btn_preivousfragment = (Button) findViewById(R.id.button_previous_fragment);

        btn_nextfragment.setOnClickListener(this);
        btn_preivousfragment.setOnClickListener(this);

        //firebaseDb = new FirebaseDb();

        init();




    } public void init()
    {
        quesListGenericTypeIndicator
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
                btn_nextfragment.setVisibility(View.GONE);
                btn_nextfragment.setVisibility(View.GONE);
                ringProgressDialog= new ProgressDialog(MainActivity.this);
                ringProgressDialog.setMessage("Loading Questions...");
                ringProgressDialog.show();
                ringProgressDialog.setCancelable(true);


                questionArrayList = dataSnapshot.getValue(quesListGenericTypeIndicator);
                Log.i("Mtag", ""+questionArrayList);
                Log.i("Mytag", " "+questionArrayList.get(0).getQUESTION());
                //return list here
                showUI();
                initAnswers();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("mTag", "Failed to read value.", databaseError.toException());

            }
        });

//        Log.i("Mytag", " "+questionArrayList.get(0).getQUESTION());
       /* return questionArrayList;*/
    }

    private void showUI() {
        questions=questionArrayList;
        Log.i("Questions ", " : " + questions);
        for (Question q : questions) {
            Log.i("Qdetails : ", "" + q.getQUESTION().toString());
        }
        ringProgressDialog.dismiss();
        btn_nextfragment.setVisibility(View.VISIBLE);
        btn_preivousfragment.setVisibility(View.VISIBLE);
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

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
            bundle.putSerializable("question_object", questions.get(0));
            firstFragment.setArguments(bundle);


            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragment_container, firstFragment).commit();

        }


    }

    public List<Question> getQuestionsList(){
        return questionArrayList  ;
    }



    private List<Question> getDataFromFirebase()
    {
        firebaseDb= new FirebaseDb();

        return firebaseDb.getQuestionsList();

    }

    private void setupAnimationWindows() {
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
        for(int i=0;i<questions.size();i++)
        {
            answers.add(-1);
        }
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
    public void setSelectedId(int selectedId)
    {
        position=(question_number);
        mScoreKeeper.setSelectedId(position,selectedId);


    }

    public int getSelectedId(int question_num)
    {
        selectedId=mScoreKeeper.getSelectedId(question_num);
        return selectedId;
    }

    private void replaceFragment(Question question) {
        // Create fragment and give it an argument specifying the article it should show
        QuestionFragment newFragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt("question_number",question_number);
        args.putSerializable("question_object",question);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
       // transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();

       /* DBHelper dbHelper=new DBHelper(getApplicationContext());
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor c = db.query(
                DBContract.DBEntry.TABLE_NAME,                     // The table to query
                null,                               // The columns to return
                DBContract.DBEntry._ID+" = ?",                                // The columns for the WHERE clause
                new String[]{"1"},                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );*/

        //TODO: connect db with fragment.Also, make UI for fragment.
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
                    Toast.makeText(getApplicationContext(),"Nothing to go forward to! Score : "+mscore,Toast.LENGTH_SHORT).show();


                    ResultFragment newFragment=ResultFragment.newInstance(mscore);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);

// Commit the transaction
                    transaction.commit();
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
        replaceFragment(question);
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
}

//TODO: 1. Clean the code as much as possible. Remove all unecessary stuff. Like Db and extra classes etc.
//TODO: 2. Do something about firebase call, make it more cleaner.
//TODO: 3. Give proper functions to buttons.
//TODO: 4. Work on UI. Make it beautiful.
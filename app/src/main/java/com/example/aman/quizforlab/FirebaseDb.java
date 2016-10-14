package com.example.aman.quizforlab;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by Aman on 10/15/2016.
 */

public class FirebaseDb {

    FirebaseDatabase mFirebaseDatabase;
    GenericTypeIndicator<List<Question>> quesListGenericTypeIndicator;

    List <Question>questionArrayList;
    public List<Question> init()
    {
        quesListGenericTypeIndicator
                =new GenericTypeIndicator<List<Question>>() {};

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mFirebaseDatabase= FirebaseDatabase.getInstance();

        DatabaseReference mDatabaseReference= mFirebaseDatabase.getReference();

        DatabaseReference mQuestionReference=mDatabaseReference.child("data");

        mQuestionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {




                questionArrayList = dataSnapshot.getValue(quesListGenericTypeIndicator);
                Log.i("Mtag", ""+questionArrayList);
                Log.i("Mytag", " "+questionArrayList.get(0).getQUESTION());
                //return list here


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("mTag", "Failed to read value.", databaseError.toException());

            }
        });

//        Log.i("Mytag", " "+questionArrayList.get(0).getQUESTION());
     return questionArrayList;
    }

    public List<Question> getQuestionsList(){
        return questionArrayList  ;
    }
}

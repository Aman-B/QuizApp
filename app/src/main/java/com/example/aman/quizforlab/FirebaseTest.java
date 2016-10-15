/*
package com.example.aman.quizforlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class FirebaseTest extends AppCompatActivity {

  //  FirebaseDatabase mFirebaseDatabase;

    TextView tv;
    String s ="Data : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);
        tv=(TextView) findViewById(R.id.textView3);

        */
/*FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mFirebaseDatabase= FirebaseDatabase.getInstance();

        DatabaseReference mDatabaseReference= mFirebaseDatabase.getReference();

        DatabaseReference mQuestionReference=mDatabaseReference.child("data");

        mQuestionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               *//*
*/
/* Iterable<DataSnapshot>snapshotIterator= dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator=snapshotIterator.iterator();
                while (iterator.hasNext()) {
*//*
*/
/*

                GenericTypeIndicator<List<Question>> quesListGenericTypeIndicator
                        =new GenericTypeIndicator<List<Question>>() {
                };
                    List <Question>questionArrayList = dataSnapshot.getValue(quesListGenericTypeIndicator);
                    Log.i("hello ", " "+questionArrayList);
                    Log.i("Mtag", ""+questionArrayList.get(0).getQUESTION());
                *//*
*/
/*
                    Log.i("mTag", " here : " + value.getANSWER() + " option a " + value.getOPT_A() +
                             " option b " + value.getOPT_B() +
                             " option c " + value.getOPT_C() +
                             " option d " + value.getOPT_D() +
                            " question " + value.getQUESTION());
                    s=s+" answer"+ value.getANSWER() + " option a " + value.getOPT_A() +
                            " option b " + value.getOPT_B() +
                            " option c " + value.getOPT_C() +
                            " option d " + value.getOPT_D() +
                            " question " + value.getQUESTION()+ " /n";*//*
*/
/*
                *//*
*/
/*}*//*

        FirebaseDb firebaseDb=new FirebaseDb(this);
        List<Question> questions=   firebaseDb.init();
        */
/*firebaseDb.getQuestionsList();*//*

                tv.setText(s+""+questions.get(0).getQUESTION());
            }

         */
/*   @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("mTag", "Failed to read value.", databaseError.toException());

            }
        });

    }*//*

}
*/

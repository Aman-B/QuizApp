package com.example.aman.quizforlab.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aman.quizforlab.Question;

/**
 * Created by Aman on 10/9/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="Quiz.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.DBEntry.TABLE_NAME + " (" +
                    DBContract.DBEntry._ID + " INTEGER PRIMARY KEY," +
                    DBContract.DBEntry.COLUMN_NAME_QUESTION + TEXT_TYPE + COMMA_SEP +
                    DBContract.DBEntry.COLUMN_NAME_OPTA + TEXT_TYPE + COMMA_SEP +
                    DBContract.DBEntry.COLUMN_NAME_OPTB + TEXT_TYPE + COMMA_SEP +
                    DBContract.DBEntry.COLUMN_NAME_OPTC + TEXT_TYPE + COMMA_SEP +
                    DBContract.DBEntry.COLUMN_NAME_OPTD + TEXT_TYPE + COMMA_SEP +
                    DBContract.DBEntry.COLUMN_NAME_ANSWER + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContract.DBEntry.TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        addQuestions(sqLiteDatabase);

    }

    private void addQuestions(SQLiteDatabase sqLiteDatabase) {

        Question q1= new Question("Which company is the largest manufacturer" +
                " of network equipment?","HP", "IBM", "CISCO","GOOGLE", "CISCO");
        this.addQuestion(q1,sqLiteDatabase);
        Question q2=new Question("Which of the following is NOT " +
                "an operating system?", "SuSe", "BIOS", "DOS","MAC", "BIOS");
        this.addQuestion(q2,sqLiteDatabase);
        Question q3=new Question("Which of the following is the fastest" +
                " writable memory?","RAM", "FLASH","Register","ROM","Register");
        this.addQuestion(q3,sqLiteDatabase);
        Question q4=new Question("Which of the following device" +
                " regulates internet traffic?",    "Router", "Bridge", "Hub","Regulator","Router");
        this.addQuestion(q4,sqLiteDatabase);
        Question q5=new Question("Which of the following is NOT an" +
                " interpreted language?","Ruby","Python","BASIC","HTML","BASIC");
        this.addQuestion(q5,sqLiteDatabase);
    }

    private void addQuestion(Question quest,SQLiteDatabase sqLiteDatabase) {
//        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.DBEntry.COLUMN_NAME_QUESTION, quest.getQUESTION());
        values.put(DBContract.DBEntry.COLUMN_NAME_ANSWER, quest.getANSWER());
        values.put(DBContract.DBEntry.COLUMN_NAME_OPTA, quest.getOPT_A());
        values.put(DBContract.DBEntry.COLUMN_NAME_OPTB, quest.getOPT_B());
        values.put(DBContract.DBEntry.COLUMN_NAME_OPTC, quest.getOPT_C());
        values.put(DBContract.DBEntry.COLUMN_NAME_OPTD,quest.getOPT_D());
        values.put(DBContract.DBEntry.COLUMN_NAME_ANSWER,quest.getANSWER());
// Inserting Row
        sqLiteDatabase.insert(DBContract.DBEntry.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}

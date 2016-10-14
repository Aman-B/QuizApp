package com.example.aman.quizforlab.database;

import android.provider.BaseColumns;

/**
 * Created by Aman on 10/9/2016.
 */

public class DBContract implements BaseColumns {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.

    private DBContract()
    {

    }
    /* Inner class that defines the table contents */
    public static class DBEntry implements BaseColumns {
        public static final String TABLE_NAME = "quiz";
        public static final String COLUMN_NAME_QUESTION="question";
        public static final String COLUMN_NAME_OPTA="opta";
        public static final String COLUMN_NAME_OPTB="optb";
        public static final String COLUMN_NAME_OPTC="optc";
        public static final String COLUMN_NAME_OPTD="optd";
        public static final String COLUMN_NAME_ANSWER="answer";

    }





}

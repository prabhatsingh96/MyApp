package com.example.fluper.myapp.appUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by fluper on 10/2/18.
 */

public class DbTest {
    private Context context;
    private SQLiteDatabase sqLiteDatabase, writableDb;
    ContentValues contentValues;
    DbHelperTest dbHelperTest;
    QuesAnswer qsq = null;
   // QuesAnswer qs;
    Cursor cursor;
    boolean b;
    public DbTest(Context context) {

        this.context = context;
        dbHelperTest = new DbHelperTest(context);
        sqLiteDatabase = dbHelperTest.getWritableDatabase();
        //writableDb = dbHelperTest.getWritableDatabase();

    }

    // INSERT QUESTION IN TABLE
    public boolean insertQuestion(String ques, String ansOne, String ansTwo, String ansThree, String ansFour, String rightAns) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DbHelperTest.QUES, ques);
        contentValues.put(DbHelperTest.ANS_ONE, ansOne);
        contentValues.put(DbHelperTest.ANS_TWO, ansTwo);
        contentValues.put(DbHelperTest.ANS_THREE, ansThree);
        contentValues.put(DbHelperTest.ANS_FOUR, ansFour);
        contentValues.put(DbHelperTest.RIGHT_ANS, rightAns);
        long result = sqLiteDatabase.insert(DbHelperTest.TABLE_NAME, null, contentValues);

        return result > 0;
    }

    // show All question & Answer

    public void retriveData() {

        cursor = sqLiteDatabase.query(DbHelperTest.TABLE_NAME,
                null, null, null, null, null, null);
         b = cursor.moveToNext();
    }
    public QuesAnswer question(){
         QuesAnswer qs= null;
        if (b) {

            int quesId = cursor.getInt(cursor.getColumnIndex(DbHelperTest.QUES_ID));
            String ques = cursor.getString(cursor.getColumnIndex(DbHelperTest.QUES));
            String ansOne = cursor.getString(cursor.getColumnIndex(DbHelperTest.ANS_ONE));
            String anTwo = cursor.getString(cursor.getColumnIndex(DbHelperTest.ANS_TWO));
            String ansThree = cursor.getString(cursor.getColumnIndex(DbHelperTest.ANS_THREE));
            String ansFour = cursor.getString(cursor.getColumnIndex(DbHelperTest.ANS_FOUR));
            String rightAns = cursor.getString(cursor.getColumnIndex(DbHelperTest.RIGHT_ANS));
            qs = new QuesAnswer(quesId, ques, ansOne, anTwo, ansThree, ansFour, rightAns);
            b = cursor.moveToNext();

        } return qs;
    }


    /// Comparision of right Answer

     public QuesAnswer rightAnswerComparision(){
         Cursor cursor1 = sqLiteDatabase.query(DbHelperTest.TABLE_NAME,
                 null, null, null, null, null, null);

         while(cursor1.moveToNext()){

             int quesId = cursor.getInt(cursor.getColumnIndex(DbHelperTest.QUES_ID));
             String ques = cursor.getString(cursor.getColumnIndex(DbHelperTest.QUES));
             String ansOne = cursor.getString(cursor.getColumnIndex(DbHelperTest.ANS_ONE));
             String anTwo = cursor.getString(cursor.getColumnIndex(DbHelperTest.ANS_TWO));
             String ansThree = cursor.getString(cursor.getColumnIndex(DbHelperTest.ANS_THREE));
             String ansFour = cursor.getString(cursor.getColumnIndex(DbHelperTest.ANS_FOUR));
             String rightAns = cursor.getString(cursor.getColumnIndex(DbHelperTest.RIGHT_ANS));
             qsq = new QuesAnswer(quesId, ques, ansOne, anTwo, ansThree, ansFour, rightAns);

         }
         return qsq;
     }



    class DbHelperTest extends SQLiteOpenHelper {

        private static final String DB_NAME = "DbTest";
        private static final String TABLE_NAME = "test";
        private static final int DB_VERSION = 1;
        private static final String QUES_ID = "ques_id";
        private static final String QUES = "ques";
        private static final String ANS_ONE = "answer_one";
        private static final String ANS_TWO = "answer_two";
        private static final String ANS_THREE = "answer_three";
        private static final String ANS_FOUR = "answer_four";
        private static final String RIGHT_ANS = "right_ans";
        private static final String DROP_TABLE = "drop table " + TABLE_NAME;


        private static final String QUERY = "create table " + TABLE_NAME + " ( " + QUES_ID
                + " integer primary key autoincrement , " + QUES
                + "  varchar(400)," + ANS_ONE + " varchar(40)," + ANS_TWO + " varchar(40), "
                + ANS_THREE + " varchar(40)," + ANS_FOUR + " varchar(40)," + RIGHT_ANS + " varchar(40));";

        public DbHelperTest(Context context) {

            super(context, DB_NAME, null, DB_VERSION);
            //getReadableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            Log.e("tag", QUERY);
            sqLiteDatabase.execSQL(QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }
    }

}

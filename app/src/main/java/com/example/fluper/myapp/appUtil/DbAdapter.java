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
 * Created by fluper on 9/2/18.
 */


public class DbAdapter {
    private Context context;
    private SQLiteDatabase sqLiteDatabase, writableDb;
    ContentValues contentValues;
    DbHelper dbHelper;
    UserDetail user = null;
    QuesAnswer qs;

    public DbAdapter(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();

    }

    // insert data in database
    public boolean insertData(String name, String email, String password, String contact) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DbHelper.NAME, name);
        contentValues.put(DbHelper.EMAIL, email);
        contentValues.put(DbHelper.PASSWORD, password);
        contentValues.put(DbHelper.CONTACT, contact);
        long result = sqLiteDatabase.insert(DbHelper.TABLE_NAME, null, contentValues);
        Toast.makeText(context, "Data Inserted with Id " + result, Toast.LENGTH_SHORT).show();
        return result > 0;
    }


    //validate data
    public boolean validate(String email, String password) {
        String column[] = {DbHelper.EMAIL, DbHelper.PASSWORD};
        String selection = DbHelper.EMAIL + " = ? and " + DbHelper.PASSWORD + " = ?";
        String selections[] = {email, password};
        Cursor cursor = sqLiteDatabase.query(DbHelper.TABLE_NAME, column, selection, selections, null, null, null);
        if (cursor.moveToNext())
            return true;
        else
            return false;
    }


    //show data


    public ArrayList<UserDetail> showData() {
        ArrayList<UserDetail> userDetailArrayList = new ArrayList<>();
        //String column[] = {DbHelper._ID,DbHelper.NAME,DbHelper.EMAIL,DbHelper.PASSWORD,DbHelper.CONTACT};
        Cursor cursor = sqLiteDatabase.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DbHelper._ID));
            String name = cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
            String email = cursor.getString(cursor.getColumnIndex(DbHelper.EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(DbHelper.PASSWORD));
            String contact = cursor.getString(cursor.getColumnIndex(DbHelper.CONTACT));

            UserDetail user = new UserDetail(id, name, email, password, contact);
            userDetailArrayList.add(user);

        }
        return userDetailArrayList;
    }

  /*  public boolean insertQuestion(String ques, String ansOne, String ansTwo, String ansThree, String ansFour, String rightAns) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DbHelper.QUES, ques);
        contentValues.put(DbHelper.ANS_ONE, ansOne);
        contentValues.put(DbHelper.ANS_TWO, ansTwo);
        contentValues.put(DbHelper.ANS_THREE, ansThree);
        contentValues.put(DbHelper.ANS_FOUR, ansFour);
        contentValues.put(DbHelper.RIGHT_ANS, rightAns);
        long result = sqLiteDatabase.insert(DbHelper.TABLE_NAME_TEST, null, contentValues);

        return result > 0;
    }

    // show All question & Answer

    public QuesAnswer retriveData() {

        Cursor cursor = writableDb.query(DbHelper.TABLE_NAME_TEST,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int quesId = cursor.getInt(cursor.getColumnIndex(DbHelper.QUES_ID));
            String ques = cursor.getString(cursor.getColumnIndex(DbHelper.QUES));
            String ansOne = cursor.getString(cursor.getColumnIndex(DbHelper.ANS_ONE));
            String anTwo = cursor.getString(cursor.getColumnIndex(DbHelper.ANS_TWO));
            String ansThree = cursor.getString(cursor.getColumnIndex(DbHelper.ANS_THREE));
            String ansFour = cursor.getString(cursor.getColumnIndex(DbHelper.ANS_FOUR));
            String rightAns = cursor.getString(cursor.getColumnIndex(DbHelper.RIGHT_ANS));
            qs = new QuesAnswer(quesId, ques, ansOne, anTwo, ansThree, ansFour, rightAns);
        }
        return qs;
    }
*/

    class DbHelper extends SQLiteOpenHelper {
        private static final String DB_NAME = "DbName";
        private static final String TABLE_NAME = "emp";
        private static final int DB_VERSION = 3;
        private static final String _ID = "id";
        private static final String NAME = "name";
        private static final String EMAIL = "email";
        private static final String PASSWORD = "password";
        private static final String CONTACT = "contact";
        private static final String DROP_TABLE_EMP = "drop table " + TABLE_NAME;
        private static final String QUERY_EMP = "create table " + TABLE_NAME + "  ( " + _ID
                + " integer primary key autoincrement , " + NAME
                + "  varchar(20)," + EMAIL + " varchar(40)," + PASSWORD + " varchar(20), "
                + CONTACT + " varchar(20));";


       /* private static final String TABLE_NAME_TEST = "test";
        private static final String QUES_ID = "ques_id";
        private static final String QUES = "ques";
        private static final String ANS_ONE = "answer_one";
        private static final String ANS_TWO = "answer_two";
        private static final String ANS_THREE = "answer_three";
        private static final String ANS_FOUR = "answer_four";
        private static final String RIGHT_ANS = "right_ans";
        private static final String DROP_TABLE_TEST = "drop table " + TABLE_NAME_TEST;
        private static final String QUERY_TEST = "create table " + TABLE_NAME_TEST + " ( " + QUES_ID
                + " integer primary key autoincrement , " + QUES
                + "  varchar(400)," + ANS_ONE + " varchar(40)," + ANS_TWO + " varchar(40), "
                + ANS_THREE + " varchar(40)," + ANS_FOUR + " varchar(40)," + RIGHT_ANS + " varchar(40));";
*/
        public DbHelper(Context context) {

            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(QUERY_EMP);
          //  sqLiteDatabase.execSQL(QUERY_TEST);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_TABLE_EMP);
           // sqLiteDatabase.execSQL(DROP_TABLE_TEST);
            onCreate(sqLiteDatabase);
        }
    }
}




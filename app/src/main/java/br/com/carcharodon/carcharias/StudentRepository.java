package br.com.carcharodon.carcharias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JGabrielFreitas on 24/12/14.
 */
public class StudentRepository extends SQLiteOpenHelper {

    public  static final String TABLE_STUDENT = "student";
    public  static final String STUDENT_NAME  = "student_name";
    public  static final String STUDENT_AGE   = "student_age";
    public  static final String STUDENT_REGISTRATION = "student_registration";
    private static final int    DATABASE_VERSION     = 2;
    public  static       String COLUMN_ID ;

    // query to create database output:
    // CREATE TABLE  "student" ( "student_name" TEXT PRIMARY KEY  NOT NULL , "student_age" INTEGER, "student_registration" INTEGER)
    public static final String DATABASE_CREATE = "CREATE TABLE '" + TABLE_STUDENT +
                                                  "' ( '" + STUDENT_NAME + "' TEXT , '" +
                                                   STUDENT_AGE + "' INTEGER, '" +
                                                   STUDENT_REGISTRATION + "' INTEGER PRIMARY KEY  NOT NULL);";

    public StudentRepository(Context context) {
        super(context, TABLE_STUDENT, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }
}

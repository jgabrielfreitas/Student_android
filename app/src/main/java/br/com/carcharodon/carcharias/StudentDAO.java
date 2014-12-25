package br.com.carcharodon.carcharias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JGabrielFreitas on 24/12/14.
 */
public class StudentDAO {

    private SQLiteDatabase sqLiteDatabase;
    private StudentRepository studentRepository;
    private String[] allColumns = {StudentRepository.COLUMN_ID, StudentRepository.STUDENT_NAME, StudentRepository.STUDENT_AGE, StudentRepository.STUDENT_REGISTRATION};

    public StudentDAO(Context context) {
        studentRepository = new StudentRepository(context);
    }

    public void open(){
        sqLiteDatabase = studentRepository.getWritableDatabase();
    }

    public void close(){
        sqLiteDatabase.close();
    }

    public boolean insertIntoBase(String name, int age, int registration){
        try {

            String query = "INSERT INTO " + StudentRepository.TABLE_STUDENT + " VALUES ( '" + name + "' , " + age + " , " + registration + " )";
            System.out.println(query);
            sqLiteDatabase.execSQL(query);
            sqLiteDatabase.close();
            Log.i("base","student saved!");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Student createOrInsertStudent(String name, Integer age, Integer registration){

        ContentValues values = new ContentValues();
        values.put(StudentRepository.STUDENT_NAME, name);
        values.put(StudentRepository.STUDENT_AGE , age);
        values.put(StudentRepository.STUDENT_REGISTRATION, registration);

        long id =  sqLiteDatabase.insert(StudentRepository.TABLE_STUDENT, null, values);

        Cursor cursor = sqLiteDatabase.query(StudentRepository.TABLE_STUDENT, allColumns, StudentRepository.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Student newStudent = cursorToComment(cursor);
        cursor.close();

        return newStudent;
    }

    public List<Student> getAllStudents(){
        List<Student> studentList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + StudentRepository.TABLE_STUDENT, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Student student = cursorToComment(cursor);
            studentList.add(student);
            cursor.moveToNext();
        }

        cursor.close();
        return studentList;
    }

    private Student cursorToComment(Cursor cursor) {

        Student student = new Student();
        student.setName(cursor.getString(0));
        student.setAge(cursor.getInt(1));
        student.setRegistration(cursor.getInt(2));

        return student;
    }


}

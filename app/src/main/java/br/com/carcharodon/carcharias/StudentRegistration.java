package br.com.carcharodon.carcharias;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class StudentRegistration extends ActionBarActivity implements View.OnClickListener{

    EditText name;
    EditText age;
    EditText registration;
    Button   save;

    private StudentDAO datasource;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        instanceViews();
    }

    private void instanceViews() {

        name = (EditText) findViewById(R.id.studentNameEditText);
        age  = (EditText) findViewById(R.id.studentAgeEditText);
        save = (Button)   findViewById(R.id.saveStudent);
        registration = (EditText) findViewById(R.id.studentRegistrationEditText);

        datasource = new StudentDAO(getApplicationContext());
        datasource.open();

        save.setOnClickListener(this);
    }

    public void onClick(View v) {

        boolean student = false;
        student = datasource.insertIntoBase(name.getText().toString(),
                Integer.parseInt(age.getText().toString()),
                Integer.parseInt(registration.getText().toString()));

        if(student)
            Toast.makeText(this, "Aluno criado com sucesso!", Toast.LENGTH_SHORT);
        else
            Toast.makeText(this, "O aluno não pode ser criado..tente novamente", Toast.LENGTH_SHORT);

    }

    protected void onStop() {
        super.onStop();
        datasource.close();
    }
}

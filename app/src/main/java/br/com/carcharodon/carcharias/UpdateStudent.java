package br.com.carcharodon.carcharias;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by JGabrielFreitas on 25/12/14.
 */
public class UpdateStudent extends ActionBarActivity implements View.OnClickListener{

    static Student studentToUpdate;
    EditText name;
    EditText age;
    EditText registration;
    Button save;
    private StudentDAO datasource;

    public void onCreate(Bundle savedInstanceState) {
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

        name.setText(studentToUpdate.getName());
        age.setText(String.valueOf(studentToUpdate.getAge()));
        registration.setText(String.valueOf(studentToUpdate.getRegistration()));

        save.setText("Atualizar aluno");
        registration.setKeyListener(null);
        save.setOnClickListener(this);

    }

    public void onClick(View v) {

        if (datasource.updateStudent(name.getText().toString(),
                                     Integer.parseInt(age.getText().toString()),
                                     Integer.parseInt(registration.getText().toString()))) {
            Toast.makeText(this, "Aluno atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            this.finish();
        } else
            Toast.makeText(this, "O aluno não pode ser atualizado.", Toast.LENGTH_SHORT).show();

    }
}

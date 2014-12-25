package br.com.carcharodon.carcharias;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ShowAllStudents extends ActionBarActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView studentsListView;
    StudentAdapter adapter;
    List<Student> studentList = new ArrayList<>();
    StudentDAO dao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_students);

        instanceViews();
    }

    private void instanceViews() {
        dao = new StudentDAO(getApplicationContext());
        dao.open();
        studentsListView = (ListView) findViewById(R.id.listStudents);

        studentList = dao.getAllStudents();
        adapter     = new StudentAdapter(studentList, this);

        studentsListView.setAdapter(adapter);
        studentsListView.setOnItemClickListener(this);
        studentsListView.setOnItemLongClickListener(this);
    }

    protected void onStop() {
        super.onStop();
        dao.close();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, UpdateStudent.class);
        UpdateStudent.studentToUpdate = studentList.get(position);
        startActivity(intent);
        this.finish();
    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        if (dao.deleteStudent(studentList.get(position).getRegistration())) {
            Toast.makeText(this, "Aluno deletado com sucesso!", Toast.LENGTH_SHORT).show();
            this.finish();
        } else
            Toast.makeText(this, "O aluno não pode ser deletado.", Toast.LENGTH_SHORT).show();

        return false;
    }
}

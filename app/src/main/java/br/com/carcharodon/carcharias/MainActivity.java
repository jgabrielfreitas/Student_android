package br.com.carcharodon.carcharias;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    ListView listMenu;
    List<String> optionsArray = new ArrayList<>();
    StudentDAO dao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanceViews();
    }

    private void instanceViews() {

        listMenu = (ListView) findViewById(R.id.listMenuListView);

        optionsArray.add("Criar aluno");
        optionsArray.add("Ver lista de alunos");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>( this,
                                                                android.R.layout.simple_list_item_1,
                                                                optionsArray );
        listMenu.setAdapter(arrayAdapter);
        listMenu.setOnItemClickListener(this);
        dao = new StudentDAO(getApplicationContext());
        dao.open();

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {

            case 0:
                Intent intent = new Intent(this, StudentRegistration.class);
                startActivity(intent);
                break;

            case 1:

                break;
        }

    }

    protected void onResume() {
        super.onResume();
        Log.i("QUERY>", StudentRepository.DATABASE_CREATE);

        if(dao.getAllStudents().size() > 0)
            for (Student student : dao.getAllStudents())
                Log.i("student_base", student.toString());
        else
            Log.i("student_base", "empty base...");
    }
}

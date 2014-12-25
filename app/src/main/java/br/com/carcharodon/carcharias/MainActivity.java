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
import android.widget.Toast;

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
        optionsArray.add("Zerar lista de alunos");

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
                doIntent(StudentRegistration.class);
                break;

            case 1:
                doIntent(ShowAllStudents.class);
                break;

            case 2:
                dao.drop();
                Toast.makeText(this, "Lista zerada!", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void doIntent(Class mClass){
        Intent intent = new Intent(this, mClass);
        startActivity(intent);
    }
}

package br.com.carcharodon.carcharias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JGabrielFreitas on 25/12/14.
 */
public class StudentAdapter extends BaseAdapter{

    List<Student> listStudents;
    Context context;

    public StudentAdapter(List<Student> listMenuItem, Context context) {
        this.listStudents = listMenuItem;
        this.context = context;
    }

    public int getCount() {
        return listStudents.size();
    }

    public Object getItem(int position) {
        return listStudents.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Student student = this.listStudents.get(position);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_item_student, parent, false);

        TextView name = (TextView) view.findViewById(R.id.rowNameTextView);
        TextView type = (TextView) view.findViewById(R.id.rowTypeTextView);
        TextView reg  = (TextView) view.findViewById(R.id.rowRegistrationTextView);

        name.setText("Nome: " + student.getName());
        reg.setText("Matrícula: " + String.valueOf(student.getRegistration()));

        if (student.getAge() < 13)
            type.setText("Tipo: Criança");
        if (student.getAge() > 13 && student.getAge() < 18)
            type.setText("Tipo: Jovem");
        if (student.getAge() > 18)
            type.setText("Tipo: Adulto");

        return view;
    }
}

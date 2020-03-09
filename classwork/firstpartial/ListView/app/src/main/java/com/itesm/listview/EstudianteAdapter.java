package com.itesm.listview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EstudianteAdapter extends BaseAdapter {

    private ArrayList<Estudiante> datos;
    private Activity activity;

    public EstudianteAdapter(ArrayList<Estudiante> datos, Activity activity) {
        this.datos = datos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return datos.get(position).getStudentId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null ){
            convertView = activity.getLayoutInflater().inflate(R.layout.row,null);
        }

        TextView id = convertView.findViewById(R.id.estudiante_id);
        TextView nombre = convertView.findViewById(R.id.estudiante_nombre);
        TextView calificacion = convertView.findViewById(R.id.estudiante_calificacion);

        Estudiante estudiantito = datos.get(position);

        id.setText(estudiantito.getStudentId() + "");
        nombre.setText(estudiantito.getNombre());
        calificacion.setText(estudiantito.getCalificacion()+"");

        return convertView;
    }
}

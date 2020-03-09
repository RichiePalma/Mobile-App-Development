package com.itesm.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // datos
        String[] datos = {"Estudiante 1", "Estudiante 2", "Estudiante 3"};
        // para conectar estos dos existe un adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_list_item_1,
            datos
        );

        ArrayList<Estudiante> estudiantitos = new ArrayList<>();
        estudiantitos.add(new Estudiante("Rafa",25,12));
        estudiantitos.add(new Estudiante("Juan",95,14));
        estudiantitos.add(new Estudiante("Hector",68,15));

        EstudianteAdapter adaptador = new EstudianteAdapter(estudiantitos,this);

        // vista
        listita = findViewById(R.id.listita);
        listita.setAdapter(adaptador);
    }

}

package com.itesm.clase01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textito;
    private TextView nombre;
    private TextView apellido;
    private Button botoncito, botoncito2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia a elementos en la GUI
        textito = findViewById(R.id.Textito);
        nombre = findViewById((R.id.Nombre));
        apellido = findViewById(R.id.Apellido);
        botoncito = findViewById((R.id.Saludar));

        textito.setText("Hola a todos");
        botoncito2=findViewById(R.id.saludo2);

        botoncito2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Que tranza puto",Toast.LENGTH_LONG);
            }
        });

        textito.setText("Hola a todos");
    }

        // método invocable/vinculable desde XML
        // debe ser público y recibir view
        public void saludar(View v){
            Log.d( "Saludar", "Hola"+nombre.getText().toString()+apellido.getText().toString());
        }
    }
}

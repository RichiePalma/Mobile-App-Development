package com.itesm.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private static final String FILE = "filename";
    private SharedPreferences prefs;
    private TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.tName);
        greeting = findViewById(R.id.tvHi);
        load();
    }

    public void load(){
        prefs = getSharedPreferences(FILE,MODE_PRIVATE);
        Toast.makeText(this,"Prefs loaded correctly, welcome " + prefs.getString("username","Stranger"),Toast.LENGTH_SHORT).show();
        greeting.setText("Howdy, "+ prefs.getString("username","Stranger"));
    }

    public void save(View v){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username",name.getText().toString());
        editor.commit();

        Toast.makeText(this,"Your name has been saved, welcome " + name.getText().toString(),Toast.LENGTH_SHORT).show();
        greeting.setText("Howdy, "+ prefs.getString("username","Stranger"));

    }

    public void eraseAll(View v){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();

        Toast.makeText(this,"See ya!", Toast.LENGTH_SHORT).show();
        greeting.setText("Howdy, "+ prefs.getString("username","Stranger"));
    }

    public void goToMenu(View v){
        Intent iMenu = new Intent(this,MenuActivity.class);
        startActivity(iMenu);

    }
}

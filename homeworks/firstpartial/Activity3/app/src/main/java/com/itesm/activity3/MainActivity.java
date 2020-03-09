package com.itesm.activity3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button bChangeToMenu;
    private EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bChangeToMenu = findViewById(R.id.bChange);
        inputName = findViewById(R.id.tName);
    }

    public void changeToMenuAct(View v){
        Intent intentito = new Intent(this,MenuActivity.class);
        //putExtra passes values between Activities
        intentito.putExtra("name",inputName.getText().toString());
        startActivity(intentito);
    }
}

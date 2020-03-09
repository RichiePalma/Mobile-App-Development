package com.itesm.activity3;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HobbiesView extends AppCompatActivity {

    private EditText hobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies_view);

        hobby = findViewById(R.id.tHobby);
    }

    public void changeBackToMenu(View v){
        Intent iMenu = new Intent();
        iMenu.putExtra("hobbies",hobby.getText().toString());
        setResult(Activity.RESULT_OK, iMenu);
        finish();
    }


}

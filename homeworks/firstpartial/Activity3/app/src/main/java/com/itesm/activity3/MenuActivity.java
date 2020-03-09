package com.itesm.activity3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private TextView saludo, hobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        saludo = findViewById(R.id.tvHi);
        hobbies = findViewById(R.id.tvHobbies);
        Intent intentote = getIntent();
        String nombre = intentote.getStringExtra("name");
        saludo.setText("Hi "+ nombre);
    }

    public void changeToHobbyView(View v){
        Intent iHobby = new Intent(this,HobbiesView.class);
        startActivityForResult(iHobby,1);
    }

    public void changeToFriendsView(View v){
        Intent iFriends = new Intent(this, Friends.class);
        startActivity(iFriends);
    }

    public void changeToMessageView(View v){
        startActivity(new Intent(this,Message.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        hobbies.setText(data.getStringExtra("hobbies"));
    }
}

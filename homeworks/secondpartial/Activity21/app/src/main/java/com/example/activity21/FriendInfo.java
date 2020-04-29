package com.example.activity21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class FriendInfo extends AppCompatActivity {

    private EditText edtName,edtAge,edtAddress,edtHobby,edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_info);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtAddress = findViewById(R.id.edtAddress);
        edtHobby = findViewById(R.id.edtHobby);
        edtPhone = findViewById(R.id.edtPhone);
        edtName.setText(getIntent().getStringExtra("name"));
        edtHobby.setText(getIntent().getStringExtra("hobby"));
        edtAge.setText(getIntent().getStringExtra("age"));
        edtPhone.setText(getIntent().getStringExtra("phone"));
        edtAddress.setText(getIntent().getStringExtra("address"));
    }

}

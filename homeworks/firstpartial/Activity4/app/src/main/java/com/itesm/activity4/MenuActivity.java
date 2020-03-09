package com.itesm.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class MenuActivity extends AppCompatActivity {
    private DBHobbies db;
    private EditText fName,fHobby;
    private Properties properties;
    private static final String PROPERTIES_FILE = "properties.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        fName = findViewById(R.id.tFriendName);
        fHobby = findViewById(R.id.tFriendHobby);

        db = new DBHobbies(this);
        properties = new Properties();
        Log.wtf("FILES",getFilesDir().toString());
        File file = new File(getFilesDir(),PROPERTIES_FILE);

        if (file.exists()) {
            Toast.makeText(this, "FILE EXISTS", Toast.LENGTH_SHORT).show();
            try{
                FileInputStream fis = openFileInput(PROPERTIES_FILE);
                properties.loadFromXML(fis);
                fis.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"FILE DOESN'T EXIST",Toast.LENGTH_SHORT).show();
            saveProperties();
        }

    }

    private void saveProperties(){
        try{
            FileOutputStream fos = openFileOutput(PROPERTIES_FILE, Context.MODE_PRIVATE);
            properties.storeToXML(fos,null);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void store(View v){
        db.save(fName.getText().toString(), fHobby.getText().toString());
        Toast.makeText(this,"Friend " + fName.getText().toString() + " with Hobby "
                 + fHobby.getText().toString() + " saved successfully",Toast.LENGTH_SHORT).show();
        fName.setText("");
        fHobby.setText("");

    }

    public void remove(View v){
        int rows = db.delete(fName.getText().toString());
        Toast.makeText(this,"Removed " + rows + " rows",Toast.LENGTH_SHORT).show();
        fName.setText("");
        fHobby.setText("");
    }

    public void search(View v){
       fHobby.setText(db.find(fName.getText().toString()));
    }
}

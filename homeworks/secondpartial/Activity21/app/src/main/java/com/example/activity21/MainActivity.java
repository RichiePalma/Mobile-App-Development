package com.example.activity21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Handler.Callback, View.OnClickListener {

    private Handler dataHandler;
    ArrayList<Friend> friendsArray;
    private RecyclerView rvFriendsList;
    JSONArray datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvFriendsList = findViewById(R.id.rvFriendsList);
        dataHandler = new Handler(Looper.getMainLooper(), this);
    }
    public void request(View v){

        Request r = new Request("https://api.jsonbin.io/b/5ea906e966e603359fe089c0", dataHandler);
        r.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        friendsArray = new ArrayList<>();
        datos = (JSONArray) msg.obj;
        try{
            for (int i=0; i<datos.length();i++) {
                Friend newFriend = new Friend();
                newFriend.setName(datos.getJSONObject(i).getString("name"));
                newFriend.setHobby(datos.getJSONObject(i).getString("hobby"));
                newFriend.setAge(datos.getJSONObject(i).getString("age"));
                newFriend.setPhone(datos.getJSONObject(i).getString("phone"));
                newFriend.setAddress(datos.getJSONObject(i).getString("address"));
                friendsArray.add(newFriend);
                //Toast.makeText(this, datos.getJSONObject(i).toString(), Toast.LENGTH_SHORT).show();
            }
            FriendAdapter friendAdapter = new FriendAdapter(friendsArray, this);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            GridLayoutManager glm = new GridLayoutManager(this, 2);


            rvFriendsList.setLayoutManager(llm);
            rvFriendsList.setAdapter(friendAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
    @Override
    public void onClick(View v) {

        int pos = rvFriendsList.getChildLayoutPosition(v);
        Intent showFriendInfo = new Intent(this, FriendInfo.class);
        showFriendInfo.putExtra("name", friendsArray.get(pos).getName());
        showFriendInfo.putExtra("hobby", friendsArray.get(pos).getHobby());
        showFriendInfo.putExtra("age", friendsArray.get(pos).getAge());
        showFriendInfo.putExtra("phone", friendsArray.get(pos).getPhone());
        showFriendInfo.putExtra("address", friendsArray.get(pos).getAddress());

        startActivity(showFriendInfo);
        try {
            Toast.makeText(this, datos.getJSONObject(pos).getString("name"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /*@Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent showFriendInfo = new Intent(this, FriendInfo.class);
        showFriendInfo.putExtra("name", friendsArray.get(i).getName());
        showFriendInfo.putExtra("hobby", friendsArray.get(i).getHobby());
        showFriendInfo.putExtra("age", friendsArray.get(i).getAge());
        showFriendInfo.putExtra("phone", friendsArray.get(i).getPhone());
        showFriendInfo.putExtra("address", friendsArray.get(i).getAddress());

        startActivity(showFriendInfo);
    }*/
}

package com.example.activity21;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder>   {


    public static class FriendViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName, txtHobby;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.tvRowName);
            txtHobby = itemView.findViewById(R.id.tvRowHobby);

        }
    }

    private ArrayList<Friend> amigos;
    private View.OnClickListener listener;

    public FriendAdapter(ArrayList<Friend> amigos, View.OnClickListener listener){

        this.amigos = amigos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_friend, parent, false);

        v.setOnClickListener(listener);

        FriendViewHolder svh = new FriendViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {

        holder.txtName.setText(amigos.get(position).getName());
        holder.txtHobby.setText(amigos.get(position).getHobby());
    }

    @Override
    public int getItemCount() {
        return amigos.size();
    }


}
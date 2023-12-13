package com.example.c3bluetoothproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList guest_id,guest_name,guest_number,guest_time;
    CustomAdapter(Context context,ArrayList guest_id, ArrayList guest_name, ArrayList guest_number, ArrayList guest_time){
            this.context=context;
            this.guest_id=guest_id;
            this.guest_name=guest_name;
            this.guest_number=guest_number;
            this.guest_time=guest_time;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.guest_id_txt.setText(String.valueOf(guest_id.get(position)));
            holder.guest_name_txt.setText(String.valueOf(guest_name.get(position)));
            holder.guest_number_txt.setText(String.valueOf(guest_number.get(position)));
            holder.guest_time_txt.setText(String.valueOf(guest_time.get(position)));
    }

    @Override
    public int getItemCount() {
        return guest_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView guest_id_txt,guest_name_txt,guest_number_txt,guest_time_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            guest_id_txt=itemView.findViewById(R.id.guest_id_txt);
            guest_name_txt=itemView.findViewById(R.id.guest_name_txt);
            guest_number_txt=itemView.findViewById(R.id.guest_number_txt);
            guest_time_txt=itemView.findViewById(R.id.guest_time_txt);
        }
    }
}

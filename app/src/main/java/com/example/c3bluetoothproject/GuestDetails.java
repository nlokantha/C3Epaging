package com.example.c3bluetoothproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class GuestDetails extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppCompatButton btn_update2,btn_clearAll;
    MyDatabaseHelper myDB;
    ArrayList<String> guest_id,guest_name,guest_number,guest_time;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_details);

        recyclerView=findViewById(R.id.recyclerView);
        btn_update2=findViewById(R.id.btn_update2);
        btn_clearAll=findViewById(R.id.btn_clearAll);

        myDB=new MyDatabaseHelper(GuestDetails.this);
        guest_id=new ArrayList<>();
        guest_name=new ArrayList<>();
        guest_number=new ArrayList<>();
        guest_time=new ArrayList<>();

        storeDataInArray();
        customAdapter=new CustomAdapter(GuestDetails.this,guest_id,guest_name,guest_number,guest_time);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GuestDetails.this));

        btn_clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }
    void storeDataInArray(){
        Cursor cursor=myDB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                guest_id.add(cursor.getString(0));
                guest_name.add(cursor.getString(1));
                guest_number.add(cursor.getString(2));
                guest_time.add(cursor.getString(3));
            }
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are You sure you want to delete all Data? ");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB= new MyDatabaseHelper(GuestDetails.this);
                myDB.deleteAllData();
                Intent intent=new Intent(GuestDetails.this,GuestDetails.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}
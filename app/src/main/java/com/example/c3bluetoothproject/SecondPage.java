package com.example.c3bluetoothproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondPage extends AppCompatActivity {

    private TextView text_worn_name,text_worn_time,text_worn_number,text_name,text_number,text_time;
    private EditText edit_name,edit_number,edit_time;
    private AppCompatButton btn_add,btn_update,btn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        initView();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SecondPage.this, GuestDetails.class);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUpdate();
            }
        });
    }
    private void initView(){
        btn_add=findViewById(R.id.btn_add);
        btn_update=findViewById(R.id.btn_update);
        btn_view=findViewById(R.id.btn_view);

        edit_name=findViewById(R.id.edit_name);
        edit_number=findViewById(R.id.edit_number);
        edit_time=findViewById(R.id.edit_time);

        text_name=findViewById(R.id.text_name);
        text_number=findViewById(R.id.text_number);
        text_time=findViewById(R.id.text_time);
        text_worn_name=findViewById(R.id.text_worn_name);
        text_worn_number=findViewById(R.id.text_worn_number);
        text_worn_time=findViewById(R.id.text_worn_time);

    }
    private void initUpdate(){
        if(validateData()){
          MyDatabaseHelper myDB=new MyDatabaseHelper(SecondPage.this);
          myDB.addGuest(edit_name.getText().toString().trim(),edit_number.getText().toString().trim(),
                  edit_time.getText().toString().trim());
        }
    }
    private boolean validateData(){
        if(edit_name.getText().toString().equals("")){
            text_worn_name.setVisibility(View.VISIBLE);
            text_worn_name.setText("Enter Guest Name");
            return false;
        }
        if(edit_number.getText().toString().equals("")){
            text_worn_number.setVisibility(View.VISIBLE);
            text_worn_number.setText("Enter Flight Number");
            return false;
        }
        if(edit_time.getText().toString().equals("")){
            text_worn_time.setVisibility(View.VISIBLE);
            text_worn_time.setText("Enter Arrival Time");
            return false;
        }

        return true;
    }
}
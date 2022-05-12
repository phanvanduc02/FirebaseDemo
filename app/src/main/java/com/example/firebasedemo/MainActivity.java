package com.example.firebasedemo;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button btnSend, btnRead;
    private EditText name, age;
    private TextView textView, textViewAge;
    private DatabaseReference databaseReference, databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        btnSend = findViewById(R.id.btnSend);
        btnRead = findViewById(R.id.btnRead);
        textView = findViewById(R.id.textView);
        textViewAge = findViewById(R.id.textViewAge);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Name");
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Age");


        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String dataName=snapshot.getValue().toString();
                            textView.setText("NAME: "+dataName);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String dataAge=snapshot.getValue().toString();
                            textViewAge.setText("AGE: "+dataAge);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataName = name.getText().toString();
                String dataAge = age.getText().toString();

            databaseReference.setValue(dataName);
            databaseReference1.setValue(dataAge);

            }
        });

    }


}
package com.example.library2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPeople extends AppCompatActivity {
     private EditText name, phone;
     private Button add;
     private DbHandler dbHandler;
     private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        context = this;
        name = findViewById(R.id. editTextName);
        phone = findViewById(R.id. editTextPhone);
        add = findViewById(R.id. buttonAdd);
        dbHandler = new DbHandler(context);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String userphone = phone.getText().toString();

                PeopleModel peopleModel = new PeopleModel(username,userphone);
                dbHandler.addPeople(peopleModel);

                startActivity(new Intent(context,People.class));
            }
        });
        }

    }

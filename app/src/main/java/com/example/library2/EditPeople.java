package com.example.library2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditPeople extends AppCompatActivity {

    private EditText name, phone;
    private Button edit;
    private DbHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_people);
        context = this;
        dbHandler = new DbHandler(context);
        name = findViewById(R.id.editTextName1);
        phone = findViewById(R.id.editTextPhone1);
        edit = findViewById(R.id.buttonEdit);
        final String id = getIntent().getStringExtra("id");
        PeopleModel peopleModel = dbHandler.getSinglePeopleModel(Integer.parseInt(id));
        name.setText(peopleModel.getName());
        phone.setText(peopleModel.getPhone());


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametext = name.getText().toString();
                String phonetext = phone.getText().toString();

                PeopleModel peopleModel = new PeopleModel(Integer.parseInt(id), nametext, phonetext);
                int state = dbHandler.updatesinglePeopleModel(peopleModel);
                startActivity(new Intent(context, People.class));
            }
        });
        }
    }

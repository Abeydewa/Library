package com.example.library2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class People extends AppCompatActivity {

     private Button add;
     private Button back;
     private ListView listView;
     private TextView count;
     Context context;
     private DbHandler dbHandler;
     private List <PeopleModel> peopleModels;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        context = this;
        dbHandler = new DbHandler(context);


        add = findViewById(R.id.add);
        back = findViewById(R.id.back);
        listView = findViewById(R.id.peoplelist );
        count = findViewById(R.id.peoplecount);

        peopleModels = new ArrayList<>();

        peopleModels = dbHandler.getAllPeopleModels();
        PeopleAdapter adapter = new PeopleAdapter(context, R.layout.single_people, peopleModels);
        listView.setAdapter(adapter);


        int countPeople = dbHandler.countPeople();
        count. setText("We have " + countPeople + " Members");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddPeople.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PeopleModel peopleModel = peopleModels.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(peopleModel.getName());
                builder.setMessage(peopleModel.getPhone());
                builder.show();
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(context,People.class));
                    }

                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener()    {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deletePeopleModel (peopleModel.getId());
                        startActivity(new Intent(context,People.class));
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, EditPeople.class );
                        intent.putExtra("id", String.valueOf(peopleModel.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();

            }

        });

        }
    }

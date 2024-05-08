package com.example.library2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.jar.Attributes;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, People.class);
                startActivity(intent);
            }

        });
        button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {

               Intent intent = new Intent(MainActivity.this, Book.class);
               startActivity(intent);
           }

        });
          button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {

               Intent intent = new Intent(MainActivity.this, Lend.class);
                startActivity(intent);
            }

        });



    }
}
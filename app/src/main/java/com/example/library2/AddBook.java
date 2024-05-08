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

public class AddBook extends AppCompatActivity {
    private EditText name, author;
    private Button add1;
    private DbHandler1 dbHandler1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        context = this;
        name = findViewById(R.id. editTextName3);
        author = findViewById(R.id. editTextAuthor3);
        add1 = findViewById(R.id. buttonAdd3);
        dbHandler1 = new DbHandler1(context);
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String userauthor = author.getText().toString();

                BookModel bookModel = new BookModel(username,userauthor);
                dbHandler1.addBook(bookModel);

                startActivity(new Intent(context,Book.class));
            }
        });
    }

}

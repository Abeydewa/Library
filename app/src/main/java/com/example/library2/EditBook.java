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

public class EditBook extends AppCompatActivity {

    private EditText name, author;
    private Button edit1;
    private DbHandler1 dbHandler1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        context = this;
        dbHandler1 = new DbHandler1(context);
        name = findViewById(R.id.editTextName2);
        author = findViewById(R.id.editTextAuthor2);
        edit1 = findViewById(R.id.buttonEdit1);
        final String id = getIntent().getStringExtra("id");
        BookModel bookModel = dbHandler1.getSingleBookModel(Integer.parseInt(id));
        name.setText(bookModel.getName());
        author.setText(bookModel.getAuthor());


        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametext = name.getText().toString();
                String authortext = author.getText().toString();

                BookModel bookModel = new BookModel(Integer.parseInt(id), nametext, authortext);
                int state = dbHandler1.updatesingleBookModel(bookModel);
                startActivity(new Intent(context, Book.class));
            }
        });
    }
}

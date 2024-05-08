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

public class Book extends AppCompatActivity {

    private Button add1;
    private Button back1;
    private ListView listView1;
    private TextView count1;
    Context context;
    private DbHandler1 dbHandler1;
    private List <BookModel> bookModels;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        context = this;
        dbHandler1 = new DbHandler1(context);


        add1 = findViewById(R.id.add1);
        back1 = findViewById(R.id.back1);
        listView1 = findViewById(R.id.booklist );
        count1 = findViewById(R.id.bookcount);

        bookModels = new ArrayList<>();

        bookModels = dbHandler1.getAllBookModels();
        BookAdapter adapter = new BookAdapter (context, R.layout.single_book, bookModels);
        listView1.setAdapter(adapter);


        int countBook = dbHandler1.countBook();
        count1. setText("We have " + countBook + " Books");


        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddBook.class));
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookModel bookModel = bookModels.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(bookModel.getName());
                builder.setMessage(bookModel.getAuthor());
                builder.show();
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(context,Book.class));
                    }

                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener()    {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler1.deleteBookModel (bookModel.getId());
                        startActivity(new Intent(context,Book.class));
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, EditBook.class );
                        intent.putExtra("id", String.valueOf(bookModel.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();

            }

        });

    }
}

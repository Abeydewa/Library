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

public class AddLend extends AppCompatActivity {
    private EditText member, book;
    private Button add2;
    private DbHandler2 dbHandler2;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lend);

        context = this;
        member = findViewById(R.id. editTextMember1);
        book = findViewById(R.id. editTextBook1);
        add2 = findViewById(R.id. buttonAdd4);

        dbHandler2 = new DbHandler2(context);

        add2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usermember = member.getText().toString();
                String userbook = book.getText().toString();
                long started = System.currentTimeMillis();

                LendModel lendModel = new LendModel(usermember,userbook,started,0);
                dbHandler2.addLend(lendModel);

                startActivity(new Intent(context,Lend.class));
            }
        });
    }

}

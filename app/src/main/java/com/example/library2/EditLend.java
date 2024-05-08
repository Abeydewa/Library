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

public class EditLend extends AppCompatActivity {

    private EditText member, book;
    private Button edit2;
    private DbHandler2 dbHandler2;
    private Context context;
    private Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lend);

        context = this;
        dbHandler2 = new DbHandler2(context);

        member = findViewById(R.id.editTextMember);
        book = findViewById(R.id.editTextBook);
        edit2 = findViewById(R.id.buttonEdit2);

        final String id = getIntent().getStringExtra("id");
        LendModel lendModel = dbHandler2.getSingleLendModel(Integer.parseInt(id));

        member.setText(lendModel.getMember());
        book.setText(lendModel.getBook());


        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String membertext = member.getText().toString();
                String booktext = book.getText().toString();
                updateDate = System.currentTimeMillis();


                LendModel lendModel = new LendModel(Integer.parseInt(id), membertext, booktext,updateDate,0);
                int state = dbHandler2.updatesingleLendModel(lendModel);
                System.out.println(state);
                startActivity(new Intent(context, Lend.class));
            }
        });
    }
}

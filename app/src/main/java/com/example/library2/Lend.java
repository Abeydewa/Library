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

public class Lend extends AppCompatActivity {

    private Button add5;
    private Button back5;
    private ListView listView5;
    private TextView count5;
    Context context;
    private DbHandler2 dbHandler2;
    private List <LendModel> lendModels;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend);
        context = this;

        dbHandler2 = new DbHandler2(context);
        add5 = findViewById(R.id.add5);
        back5 = findViewById(R.id.back5);
        listView5 = findViewById(R.id.booklist2 );
        count5 = findViewById(R.id.bookcount2);
        lendModels = new ArrayList<>();

        lendModels = dbHandler2.getAllLendModels();

        LendAdapter adapter = new LendAdapter (context, R.layout.single_lend, lendModels);

        listView5.setAdapter(adapter);


        int countLend = dbHandler2.countLend();
        count5. setText("We have Lend " + countLend + " Books");


        add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddLend.class));
            }
        });

        back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });

        listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LendModel lendModel = lendModels.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(lendModel.getMember());
                builder.setMessage(lendModel.getBook());
                builder.show();
                builder.setPositiveButton("Returned", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lendModel.setFinished(System.currentTimeMillis());
                        dbHandler2.updatesingleLendModel(lendModel);
                        startActivity(new Intent(context,Lend.class));
                    }

                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener()    {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler2.deleteLendModel (lendModel.getId());
                        startActivity(new Intent(context,Lend.class));
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, EditLend.class );
                        intent.putExtra("id", String.valueOf(lendModel.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();

            }

        });

    }
}

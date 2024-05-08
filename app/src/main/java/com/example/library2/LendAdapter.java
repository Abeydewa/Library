package com.example.library2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LendAdapter extends ArrayAdapter<LendModel> {
    private Context context;
    private int resource;
    List<LendModel> lendModels;

    LendAdapter(Context context, int resource, List<LendModel> lendModels){

        super(context, resource, lendModels);
        this.context = context;
        this.resource = resource;
        this.lendModels = lendModels;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView member = row.findViewById(R.id.member);
        TextView book = row.findViewById(R.id.book);
        ImageView imageView = row.findViewById(R.id.onGoing);

        LendModel lendModel = lendModels.get(position);
        member.setText(lendModel.getMember());
        book.setText(lendModel.getBook());
        imageView.setVisibility(row.INVISIBLE);

         if(lendModel.getFinished() > 0){
          imageView.setVisibility(View.VISIBLE);
         }
        return row;
    }
}

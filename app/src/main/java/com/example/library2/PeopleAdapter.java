package com.example.library2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PeopleAdapter extends ArrayAdapter<PeopleModel> {
   private Context context;
   private int resource;
   List<PeopleModel> peopleModels;

    PeopleAdapter(Context context, int resource, List<PeopleModel> peopleModels){

        super(context, resource, peopleModels);
        this.context = context;
        this.resource = resource;
        this.peopleModels = peopleModels;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView name = row.findViewById(R.id.name);
        TextView phone = row.findViewById(R.id.phone);
        //ImageView imageView = row.findViewById(R.id.onGoing);

        PeopleModel peopleModel = peopleModels.get(position);
        name.setText(peopleModel.getName());
        phone.setText(peopleModel.getPhone());
        //imageView.setVisibility(row.INVISIBLE);

       // if(toDo.getFinished() > 0){
          //  imageView.setVisibility(View.VISIBLE);
       // }
        return row;
    }
}

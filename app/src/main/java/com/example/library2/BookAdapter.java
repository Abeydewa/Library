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

public class BookAdapter extends ArrayAdapter<BookModel> {
    private Context context;
    private int resource;
    List<BookModel> bookModels;

    BookAdapter(Context context, int resource, List<BookModel> bookModels){

        super(context, resource, bookModels);
        this.context = context;
        this.resource = resource;
        this.bookModels = bookModels;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView name = row.findViewById(R.id.name);
        TextView author = row.findViewById(R.id.author);
        //ImageView imageView = row.findViewById(R.id.onGoing);

        BookModel bookModel = bookModels.get(position);
        name.setText(bookModel.getName());
        author.setText(bookModel.getAuthor());
        //imageView.setVisibility(row.INVISIBLE);

        // if(toDo.getFinished() > 0){
        //  imageView.setVisibility(View.VISIBLE);
        // }
        return row;
    }
}

package com.example.library2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class DbHandler1 extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME1 = "book";
    private static final String TABLE_NAME1 = "book";

    private static final String BOOK_ID = "id";
    private static final String BOOK_NAME = "name";
    private static final String BOOK_AUTHOR = "author";

    public DbHandler1(@Nullable Context context) {
        super(context, DB_NAME1, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME1+" " +
                "("
                +BOOK_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +BOOK_NAME + " TEXT,"
                +BOOK_AUTHOR + " TEXT" + " );";

        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME1;

        db.execSQL(DROP_TABLE_QUERY);

        onCreate(db);
    }
    public void addBook(BookModel bookModel){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BOOK_NAME,bookModel.getName());
        contentValues.put(BOOK_AUTHOR,bookModel.getAuthor());

        sqLiteDatabase.insert(TABLE_NAME1,null,contentValues);
        sqLiteDatabase.close();

    }
    public int countBook(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME1;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public List<BookModel> getAllBookModels(){

        List<BookModel> bookModels = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME1;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {

                BookModel bookModel = new BookModel();

                bookModel.setId(cursor.getInt(0));
                bookModel.setName(cursor.getString(1));
                bookModel.setAuthor(cursor.getString(2));



                bookModels.add(bookModel);
            }while (cursor.moveToNext());
        }
        return bookModels;
    }

    public void deleteBookModel(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME1, BOOK_ID + "=?", new String[]{String.valueOf(id)});
        db.close();

    }
    public BookModel getSingleBookModel(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME1,new String[]{BOOK_ID,BOOK_NAME,BOOK_AUTHOR},
                BOOK_ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        BookModel bookModel;
        if(cursor != null){
            cursor.moveToFirst();
            bookModel = new BookModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            return bookModel;
        }
        return null;
    }

    public int updatesingleBookModel(BookModel bookModel){


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BOOK_NAME,bookModel.getName());
        contentValues.put(BOOK_AUTHOR,bookModel.getAuthor());

        int status = db.update(TABLE_NAME1, contentValues, BOOK_ID + "=?",
                new String[]{String.valueOf(bookModel.getId())});

        db.close();;
        return status;
    }




}


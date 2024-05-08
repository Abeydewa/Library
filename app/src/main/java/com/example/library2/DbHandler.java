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

public class DbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "people";
    private static final String TABLE_NAME = "people";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME + " TEXT,"
                +PHONE + " TEXT" + " );";

        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;

        db.execSQL(DROP_TABLE_QUERY);

        onCreate(db);
    }
    public void addPeople(PeopleModel peopleModel){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,peopleModel.getName());
        contentValues.put(PHONE,peopleModel.getPhone());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

    }
    public int countPeople(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public List<PeopleModel> getAllPeopleModels(){

        List<PeopleModel> peopleModels = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {

                PeopleModel peopleModel = new PeopleModel();

                peopleModel.setId(cursor.getInt(0));
                peopleModel.setName(cursor.getString(1));
                peopleModel.setPhone(cursor.getString(2));



                peopleModels.add(peopleModel);
            }while (cursor.moveToNext());
        }
        return peopleModels;
    }

    public void deletePeopleModel(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
        db.close();

    }
    public PeopleModel getSinglePeopleModel(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,NAME,PHONE},
                ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        PeopleModel peopleModel;
        if(cursor != null){
            cursor.moveToFirst();
            peopleModel = new PeopleModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            return peopleModel;
        }
        return null;
    }

    public int updatesinglePeopleModel(PeopleModel peopleModel){


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,peopleModel.getName());
        contentValues.put(PHONE,peopleModel.getPhone());

        int status = db.update(TABLE_NAME, contentValues, ID + "=?",
                new String[]{String.valueOf(peopleModel.getId())});

        db.close();;
        return status;
    }




}


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

public class DbHandler2 extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME2 = "lend";
    private static final String TABLE_NAME2 = "lend";

    private static final String LEND_ID = "id";
    private static final String LEND_MEMBER = "member";
    private static final String LEND_BOOK = "book";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public DbHandler2(@Nullable Context context) {
        super(context, DB_NAME2, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME2+" " +
                "("
                +LEND_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +LEND_MEMBER + " TEXT,"
                +LEND_BOOK + " TEXT," + STARTED+ " TEXT,"
                +FINISHED+" TEXT" +
                " );";

        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME2;

        db.execSQL(DROP_TABLE_QUERY);

        onCreate(db);
    }
    public void addLend(LendModel lendModel){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(LEND_MEMBER,lendModel.getMember());
        contentValues.put(LEND_BOOK,lendModel.getBook());
        contentValues.put(STARTED,lendModel.getStarted());
        contentValues.put(FINISHED,lendModel.getFinished());

        sqLiteDatabase.insert(TABLE_NAME2,null,contentValues);
        sqLiteDatabase.close();

    }
    public int countLend(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME2;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public List<LendModel> getAllLendModels(){

        List<LendModel> lendModels = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME2;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {

                LendModel lendModel = new LendModel();

                lendModel.setId(cursor.getInt(0));
                lendModel.setMember(cursor.getString(1));
                lendModel.setBook(cursor.getString(2));
                lendModel.setStarted(cursor.getLong(3));
                lendModel.setFinished(cursor.getLong(4));



                lendModels.add(lendModel);
            }while (cursor.moveToNext());
        }
        return lendModels;
    }

    public void deleteLendModel(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME2, LEND_ID + "=?", new String[]{String.valueOf(id)});
        db.close();

    }
    public LendModel getSingleLendModel(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME2,new String[]{LEND_ID,LEND_MEMBER,LEND_BOOK,STARTED, FINISHED},
                LEND_ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        LendModel lendModel;
        if(cursor != null){
            cursor.moveToFirst();
            lendModel = new LendModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)
            );
            return lendModel;
        }
        return null;
    }

    public int updatesingleLendModel(LendModel lendModel){


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(LEND_MEMBER,lendModel.getMember());
        contentValues.put(LEND_BOOK,lendModel.getBook());
        contentValues.put(STARTED,lendModel.getStarted());
        contentValues.put(FINISHED,lendModel.getFinished());

        int status = db.update(TABLE_NAME2, contentValues, LEND_ID + "=?",
                new String[]{String.valueOf(lendModel.getId())});

        db.close();;
        return status;
    }




}


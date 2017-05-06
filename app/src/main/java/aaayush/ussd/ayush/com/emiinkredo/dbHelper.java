package aaayush.ussd.ayush.com.emiinkredo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ayush Singh on 5/6/2017.
 */





public class dbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Values.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "entries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_UID = "name";
    public static final String COLUMN_SUM = "gender";
    public static final String COLUMN_TENURE = "age";
    public static final String TYPE = "type";

    public dbHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_UID + " TEXT PRIMARY KEY, " +
                        COLUMN_SUM + " TEXT, " +
                        COLUMN_TENURE + " TEXT, " +
                        TYPE + " INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String uid, String sum, String tenure, int a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_UID, uid);
        contentValues.put(COLUMN_SUM, sum);
        contentValues.put(COLUMN_TENURE, tenure);
        contentValues.put(TYPE, a);

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }






}
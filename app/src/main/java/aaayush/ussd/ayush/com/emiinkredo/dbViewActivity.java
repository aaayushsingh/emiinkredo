package aaayush.ussd.ayush.com.emiinkredo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class dbViewActivity extends AppCompatActivity {

    private ListView listView;
    dbHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_view);

        dbh = new dbHelper(this);

        final Cursor cursor = dbh.getAllPersons();
        String [] columns = new String[] {
                dbHelper.COLUMN_TENURE,
                dbHelper.COLUMN_SUM,
                dbHelper.TYPE
        };
        int [] widgets = new int[] {
                R.id.UID,
                R.id.Name,
                R.id.Type
        };

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.info,
                cursor, columns, widgets, 0);
        listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(cursorAdapter);


    }

}

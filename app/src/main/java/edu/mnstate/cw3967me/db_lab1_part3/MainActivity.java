package edu.mnstate.cw3967me.db_lab1_part3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EmpHelper empHelper;
    private SQLiteDatabase db;
    EditText empName;
    EditText empAdr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empName = (EditText) findViewById(R.id.name);
        empAdr = (EditText) findViewById(R.id.address);

        empHelper = new EmpHelper(this);//this = current context
    }
    public void addEmp(View v){
        String eName = empName.getText().toString();
        String eAdr = empAdr.getText().toString();
        db = empHelper.getWritableDatabase();
        //ContentValues maps a key to a value
        ContentValues cVals = new ContentValues();
        cVals.put(empHelper.EMPNAME,eName);
        cVals.put(empHelper.EMPADDRESS, eAdr);
        long id = db.insert(empHelper.TABLE_NAME, null, cVals);
        if (id>0){
            Message.message(this, "success- added to db");
        }else{
            Message.message(this, "unsuccessful-- no add");
        }
    }
    public void viewColNames(View v){
        db = empHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM empTable WHERE 0", null);
        try{
            String[] colNames = c.getColumnNames();
            Message.message(this, "Column Names:" + colNames[0] + " " + colNames[1] + " " + colNames[2]);
        }finally {
            c.close();
        }
    }
}

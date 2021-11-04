package com.dawcyn.common;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.dawcyn.common.Constants.FIRST_COLUMN;
import static com.dawcyn.common.Constants.FORTH_COLUMN;
import static com.dawcyn.common.Constants.SECOND_COLUMN;
import static com.dawcyn.common.Constants.THIRD_COLUMN;

public class Saved extends AppCompatActivity {
    ListView lv;
    DatabaseHelper db = new DatabaseHelper(this);
    TextView tvEmpty,tvCount;
    private ArrayList<HashMap<String,String>> list;
    Cursor cursor;
    int COUT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        tvEmpty = (TextView) findViewById(R.id.tvEmptyStatus);
        tvCount = (TextView) findViewById(R.id.tvTotalCartCount);

        getSavedDetails();
        getCheckSavedCount();
    }

    private void getSavedDetails() {
        try{
            SQLiteDatabase sqLiteDatabase;
            lv = (ListView) findViewById(R.id.list);
            list = new ArrayList<HashMap<String, String>>();

            //Cart cart = new Cart();
            sqLiteDatabase = db.getReadableDatabase();
            cursor = db.getSavedInfo(sqLiteDatabase);
            list.clear();

            while(cursor.moveToNext())
            {
                String code = cursor.getString(0);
                String name = cursor.getString(1);
                String price = cursor.getString(2);
                String image = cursor.getString(3);

                HashMap<String,String> temp = new HashMap<>();
                temp.put(FIRST_COLUMN,code);
                temp.put(SECOND_COLUMN,name);
                temp.put(THIRD_COLUMN,price);
                temp.put(FORTH_COLUMN, image);

                list.add(temp);

                SavedAdapter adapter = new SavedAdapter(this,list);
                lv.setAdapter(adapter);

                if (list.size()>0){
                    tvEmpty.setVisibility(View.INVISIBLE);
                }else {
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }

        }catch (Exception ex) {
            //Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            new SuperClass().setMessage(Saved.this, ex.toString());
            ex.printStackTrace();
        }
    }

    private void getCheckSavedCount() {
        try{
            double total = 0;
            SQLiteDatabase sqLiteDatabase;
            //lv = (ListView) findViewById(R.id.lv_voters);
            TextView ttl = (TextView) findViewById(R.id.tvTotalSavedCount);
            //TextView tt2 = (TextView) findViewById(R.id.tvNetTotal);
            list = new ArrayList<HashMap<String, String>>();

            sqLiteDatabase = db.getReadableDatabase();
            Cursor cursor = db.getSavedCount(sqLiteDatabase);
            list.clear();

            while(cursor.moveToNext())
            {
                String ID = cursor.getString(0);
                //String cost = cursor.getString(1);

                //total = total + Double.parseDouble(cost);

            }

             COUT =cursor.getCount();
            //String word = String.valueOf(cout+1);
            ttl.setText(String.valueOf(COUT));
            // tt2.setText(String.valueOf(total));

        }catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }

    public void onClickUnsavedProduct(View view) {

        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(this);
        dialog.setTitle(R.string.app_name);
        dialog.setMessage("About to clear all ("+ COUT +") \nsaved products collections?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(Saved.this, "Still under Construction!...", Toast.LENGTH_LONG).show();

                try{
                    new DatabaseHelper(Saved.this).clearSavedProduct();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    getSavedDetails();
                    getCheckSavedCount();
                }


            }
        });
        dialog.setNegativeButton("NO", null);
        dialog.show();
    }
}

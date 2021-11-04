package com.dawcyn.common;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.dawcyn.common.Constants.FIFTH_COLUMN;
import static com.dawcyn.common.Constants.FIRST_COLUMN;
import static com.dawcyn.common.Constants.FORTH_COLUMN;
import static com.dawcyn.common.Constants.SECOND_COLUMN;
import static com.dawcyn.common.Constants.THIRD_COLUMN;

public class ViewCart extends AppCompatActivity {
    ListView lv;
    DatabaseHelper db = new DatabaseHelper(this);
    TextView tvEmpty,tvCount,tvTotal;
    private ArrayList<HashMap<String,String>> list;
    Cursor cursor;
    int COUT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        tvEmpty = (TextView) findViewById(R.id.tvEmptyStatus);
        tvCount = (TextView) findViewById(R.id.tvTotalCartCount);
        tvTotal = (TextView) findViewById(R.id.tvNetTotal);

        getCheckCartCount();
        getCartDetails();

    }

    private void getCartDetails() {
        try{
            SQLiteDatabase sqLiteDatabase;
            lv = (ListView) findViewById(R.id.list);
            //list = new ArrayList<HashMap<String, String>>();

            Cart cart = new Cart();
            sqLiteDatabase = db.getReadableDatabase();
            cursor = db.getCartInfo(sqLiteDatabase);
            list.clear();

            while(cursor.moveToNext())
            {
                String name = cursor.getString(0);
                String price = cursor.getString(1);
                String qty = cursor.getString(2);
                String cost = cursor.getString(3);
                String image = cursor.getString(4);

                HashMap<String,String> temp = new HashMap<>();
                temp.put(FIRST_COLUMN,name);
                temp.put(SECOND_COLUMN,qty);
                temp.put(THIRD_COLUMN,price);
                temp.put(FORTH_COLUMN, cost);
                temp.put(FIFTH_COLUMN, image);

                list.add(temp);

                CartAdapter adapter = new CartAdapter(this,list);
                lv.setAdapter(adapter);

                if (list.size()>0){
                    tvEmpty.setVisibility(View.INVISIBLE);
                }else {
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }

        }catch (Exception ex) {
            //Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            new SuperClass().setMessage(ViewCart.this, ex.toString());
            ex.printStackTrace();
        }
    }

    private void getCheckCartCount() {
        try{
            double total = 0;
            SQLiteDatabase sqLiteDatabase;
            //lv = (ListView) findViewById(R.id.lv_voters);
            TextView ttl = (TextView) findViewById(R.id.tvTotalCartCount);
            TextView tt2 = (TextView) findViewById(R.id.tvNetTotal);
            list = new ArrayList<HashMap<String, String>>();

            sqLiteDatabase = db.getReadableDatabase();
            Cursor cursor = db.getAddToCartCount(sqLiteDatabase);
            list.clear();

            while(cursor.moveToNext())
            {
                String ID = cursor.getString(0);
                String cost = cursor.getString(1);

                total = total + Double.parseDouble(cost);

            }

            COUT =cursor.getCount();
            //String word = String.valueOf(cout+1);
            ttl.setText(String.valueOf(COUT));
            tt2.setText(String.valueOf(total));

        }catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }


    public void onClickClearCart(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.app_name);
        dialog.setMessage("Are you sure to clear ("+ COUT +") \nproducts from shop cart?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Toast.makeText(ViewCart.this, "Still under Construction!...", Toast.LENGTH_LONG).show();
                try{
                    new DatabaseHelper(ViewCart.this).clearCartCollection();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    getCartDetails();
                    getCheckCartCount();

                }
            }
        });
        dialog.setNegativeButton("NO", null);
        dialog.show();
    }

    public void onClickPlaceOrderNow(View view) {
        Intent intent = new Intent(getApplicationContext(), ChoosePayMethod.class);
        intent.putExtra("cost", Double.parseDouble(tvTotal.getText().toString()));
        startActivity(intent);


    }

    public void onClickCartBack(View view) {finish();
    }
}

package com.dawcyn.common;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.HashMap;

public class SubStore extends AppCompatActivity {
    ViewFlipper v_flipper;
    SuperClass call = new SuperClass();
    ListView lv;
    String category;
    TextView tvCategoryName,tvCartCount;
    ImageView imgCartIcon;

    DatabaseHelper db = new DatabaseHelper(this);
    private ArrayList<HashMap<String,String>> list;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        getCartCount();
        //super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_store);

        lv = (ListView) findViewById(R.id.list);

        category = getIntent().getStringExtra("category");
        tvCategoryName = (TextView) findViewById(R.id.tvCategoryName);
        tvCategoryName.setText(tvCategoryName.getText().toString()+" - "+ category);

        v_flipper = (ViewFlipper) findViewById(R.id.v_flipper);
        tvCartCount = (TextView) findViewById(R.id.tvCartCount);
        imgCartIcon = (ImageView) findViewById(R.id.imgCartIcon);
        getCartCount();

        for (int i = 0; i < call.images.length; i++){
            call.flipperImages(this, call.images[i], v_flipper);

        }


        if (category.equals("Shirts")){
            lv = (ListView) findViewById(R.id.list);
            Adapter adapter = new Adapter(this, call.shirt_name, call.shirt_old_price, call.shirt_discount, call.shirts);
            lv.setAdapter(adapter);
            call.setListViewHeightBasedOnItems(lv);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    double disc = call.shirt_discount[position]/100;
                    double oldp = call.shirt_old_price[position];
                    double newp = oldp - (oldp * disc);

                    Intent intent =new Intent(SubStore.this, ProductDetails.class);
                    intent.putExtra("product", call.shirts[position]);
                    intent.putExtra("newPrice", newp);
                    intent.putExtra("old-price", call.shirt_old_price[position]);
                    intent.putExtra("productName", call.shirt_name[position]);
                    startActivity(intent);
                }
            });
        }else if (category.equals("Jeans")){
            lv = (ListView) findViewById(R.id.list);
            Adapter adapter = new Adapter(this, call.jeans_name, call.jeans_old_price, call.jeans_discount, call.jeans);
            lv.setAdapter(adapter);
            call.setListViewHeightBasedOnItems(lv);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    double disc = call.jeans_discount[position]/100;
                    double oldp = call.jeans_old_price[position];
                    double newp = oldp - (oldp * disc);

                    Intent intent =new Intent(SubStore.this, ProductDetails.class);
                    intent.putExtra("product", call.jeans[position]);
                    intent.putExtra("newPrice", newp);
                    intent.putExtra("old-price", call.jeans_old_price[position]);
                    intent.putExtra("productName", call.jeans_name[position]);
                    startActivity(intent);
                }
            });
        }else if (category.equals("Shoes")){
            lv = (ListView) findViewById(R.id.list);
            Adapter adapter = new Adapter(this, call.shoes_name, call.shoes_old_price, call.shoes_discount, call.shoes);
            lv.setAdapter(adapter);
            call.setListViewHeightBasedOnItems(lv);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    double disc = call.shoes_discount[position]/100;
                    double oldp = call.shoes_old_price[position];
                    double newp = oldp - (oldp * disc);

                    Intent intent =new Intent(SubStore.this, ProductDetails.class);
                    intent.putExtra("product", call.shoes[position]);
                    intent.putExtra("newPrice", newp);
                    intent.putExtra("old-price", call.shoes_old_price[position]);
                    intent.putExtra("productName", call.shoes_name[position]);
                    startActivity(intent);
                }
            });
        }else if (category.equals("Slippers")){
            lv = (ListView) findViewById(R.id.list);
            Adapter adapter = new Adapter(this, call.slippers_name, call.slippers_old_price, call.slippers_discount, call.slippers);
            lv.setAdapter(adapter);
            call.setListViewHeightBasedOnItems(lv);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    double disc = call.slippers_discount[position]/100;
                    double oldp = call.slippers_old_price[position];
                    double newp = oldp - (oldp * disc);

                    Intent intent =new Intent(SubStore.this, ProductDetails.class);
                    intent.putExtra("product", call.slippers[position]);
                    intent.putExtra("newPrice", newp);
                    intent.putExtra("old-price", call.slippers_old_price[position]);
                    intent.putExtra("productName", call.slippers_name[position]);
                    startActivity(intent);
                }
            });
        }else if (category.equals("Bags")){
            lv = (ListView) findViewById(R.id.list);
            Adapter adapter = new Adapter(this, call.bags_name, call.bags_old_price, call.bags_discount, call.bags);
            lv.setAdapter(adapter);
            call.setListViewHeightBasedOnItems(lv);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    double disc = call.bags_discount[position]/100;
                    double oldp = call.bags_old_price[position];
                    double newp = oldp - (oldp * disc);

                    Intent intent =new Intent(SubStore.this, ProductDetails.class);
                    intent.putExtra("product", call.bags[position]);
                    intent.putExtra("newPrice", newp);
                    intent.putExtra("old-price", call.bags_old_price[position]);
                    intent.putExtra("productName", call.bags_name[position]);
                    startActivity(intent);
                }
            });
        }
    }

    private void getCartCount() {
        try{
            String status = "";
            SQLiteDatabase sqLiteDatabase;
            //lv = (ListView) findViewById(R.id.lv_voters);
            TextView ttl = (TextView) findViewById(R.id.tvCartCount);
            list = new ArrayList<HashMap<String, String>>();

            sqLiteDatabase = db.getReadableDatabase();
            Cursor cursor = db.getAddToCartCount(sqLiteDatabase);
            list.clear();

            while(cursor.moveToNext())
            {
                String ID = cursor.getString(0);
                String cost = cursor.getString(1);

            }

            int cout =cursor.getCount();
            //String word = String.valueOf(cout+1);
            ttl.setText(String.valueOf(cout));
            if (cout>0){
                imgCartIcon.setBackgroundResource(R.drawable.loaded_cart);
            }else{
                imgCartIcon.setBackgroundResource(R.drawable.empty_cart);
            }

        }catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }

    public void onClickSubAddtoCart(View view) {startActivity(new Intent(SubStore.this, ViewCart.class));
    }

    public void onClickSubBack(View view) {finish();
    }
}
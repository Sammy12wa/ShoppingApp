package com.dawcyn.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop extends AppCompatActivity {

    String USER = "Shop";
    ViewFlipper v_flipper;

    SuperClass call = new SuperClass();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //USER = getIntent().getStringExtra("user");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_shop);
        setSupportActionBar(toolbar);

        if (USER.equals("Designer")){
           try{
               getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           }catch (Exception e){
               e.printStackTrace();
           }
        }

        v_flipper = (ViewFlipper) findViewById(R.id.v_flipper);

        for (int i = 0; i < call.images.length; i++){
            call.flipperImages(this, call.images[i], v_flipper);
        }

        lv = (ListView) findViewById(R.id.list);
        Adapter adapter = new Adapter(this, call.name, call.oldPrice, call.discount, call.products);
        lv.setAdapter(adapter);
        call.setListViewHeightBasedOnItems(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                double disc = call.discount[position]/100;
                double oldp = call.oldPrice[position];
                double newp = oldp - (oldp * disc);

                Intent intent =new Intent(Shop.this, ProductDetails.class);
                intent.putExtra("product", call.products[position]);
                intent.putExtra("newPrice", newp);
                intent.putExtra("old-price", call.oldPrice[position]);
                intent.putExtra("productName", call.name[position]);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if (USER.equals("Shop")){
            getMenuInflater().inflate(R.menu.menu_shop, menu);
        }else if (USER.equals("Designer")) {
            getMenuInflater().inflate(R.menu.menu_deco, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profile) {
            startActivity(new Intent(Shop.this, Profile.class));
            //return true;
        }else if (id == R.id.exit){
            finish();
        }else if (id == R.id.shop_cart){
            startActivity(new Intent(Shop.this, ViewCart.class));
        }else if (id == R.id.savedProduct){
            startActivity(new Intent(Shop.this, Saved.class));
        }else if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickShirtsStore(View view) {
        //startActivity(new Intent(Store.this, SubStore.class));
        Intent callIntent = new Intent(Shop.this, SubStore.class);
        callIntent.putExtra("category", "Shirts");
        startActivity(callIntent);

    }

    public void onClickJeansStore(View view) {
        Intent callIntent = new Intent(Shop.this, SubStore.class);
        callIntent.putExtra("category", "Jeans");
        startActivity(callIntent);
    }

    public void onClickShoesStore(View view) {
        Intent callIntent = new Intent(Shop.this, SubStore.class);
        callIntent.putExtra("category", "Shoes");
        startActivity(callIntent);
    }

    public void onClickSlippersStore(View view) {
        Intent callIntent = new Intent(Shop.this, SubStore.class);
        callIntent.putExtra("category", "Slippers");
        startActivity(callIntent);
    }

    public void onClickBagsStore(View view) {
        Intent callIntent = new Intent(Shop.this, SubStore.class);
        callIntent.putExtra("category", "Bags");
        startActivity(callIntent);
    }

}

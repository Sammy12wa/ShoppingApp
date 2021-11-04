package com.dawcyn.common;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductDetails extends AppCompatActivity {

    TextView tvNewPrice,tvOldPrice,tvQuantity,tvCost,tvName,tvCartCount;
    int PRODUCT,CART_COUNT;
    Double PRICE;
    String NAME, CODE="";
    ImageView imgIcon,imgCartIcon,imgSavedIcon;
    boolean check = true;


    SuperClass call = new SuperClass();
    DatabaseHelper db = new DatabaseHelper(this);
    private ArrayList<HashMap<String,String>> list;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        getCheckCartCount();
        //setSavedProductIcon();
        //super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        PRODUCT = getIntent().getIntExtra("product", -1);
        PRICE =  getIntent().getDoubleExtra("newPrice", -1);
        NAME = getIntent().getStringExtra("productName");

        tvNewPrice = (TextView) findViewById(R.id.txtNewPrice);
        tvOldPrice = (TextView) findViewById(R.id.txtOldPrice);
        tvQuantity = (TextView) findViewById(R.id.tvQuantityCount);
        tvCost = (TextView) findViewById(R.id.txtTotalCost);
        tvName = (TextView) findViewById(R.id.txtProductName);
        imgIcon = (ImageView) findViewById(R.id.imgProductIcon);
        imgCartIcon = (ImageView) findViewById(R.id.imgCartIcon);
        tvCartCount = (TextView) findViewById(R.id.tvCartCount);
        imgSavedIcon = (ImageView) findViewById(R.id.imgSavedProduct);

        tvNewPrice.setText(String.valueOf(PRICE));
        tvName.setText(NAME);
        imgIcon.setImageResource(PRODUCT);

        CODE = db.searchProductCode(String.valueOf(PRODUCT));
        setSavedProductIcon();
        setTotalCost(tvCost, tvNewPrice.getText().toString(), tvQuantity.getText().toString());
    }

    public void onClickDetailsBack(View view) {finish();
    }

    public void onSubtractQuantity(View view) {
        if (Integer.parseInt(tvQuantity.getText().toString())> 1){
            tvQuantity.setText(String.valueOf(Integer.parseInt(tvQuantity.getText().toString())-1));
            setTotalCost(tvCost, tvNewPrice.getText().toString(), tvQuantity.getText().toString());
        }
    }

    public void onAddQuantity(View view) {
        tvQuantity.setText(String.valueOf(Integer.parseInt(tvQuantity.getText().toString())+1));
        setTotalCost(tvCost, tvNewPrice.getText().toString(), tvQuantity.getText().toString());
    }

    public void onClickAddToCart(View view) {

        String name = tvName.getText().toString();
        String quantity = tvQuantity.getText().toString();
        String price = tvNewPrice.getText().toString();
        String cost = tvCost.getText().toString();

        boolean isInserted = db.addToCart(new Cart(name,price,quantity,cost,new SuperClass().getImageData(new SuperClass().convertImageViewToBitmap(imgIcon))));

        //new SuperClass().setMessage(ProductDetails.this, getImageData(convertImageViewToBitmap(imgIcon)));

        if (isInserted){
            // Toast.makeText(ProductDetails.this, "", Toast.LENGTH_LONG).show();
            getAddCartCount();

        }else {
            //Toast.makeText(ProductDetails.this, "", Toast.LENGTH_LONG).show();
        }

    }

    public void setTotalCost(TextView textView, String price_per_unit, String quantity){
        DecimalFormat precision = new DecimalFormat("#.00");

        //txtTextField.setText(precision.format(dblVariable));
        double cost = 0;
        int qty = Integer.parseInt(quantity);
        double price = Double.parseDouble(price_per_unit);

        cost = price * qty;
        textView.setText(precision.format(cost));
    }


    private void getAddCartCount() {
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
                String cost= cursor.getString(1);

            }

            int cout =cursor.getCount();
            String word = String.valueOf(CART_COUNT+1);
            imgCartIcon.setBackgroundResource(R.drawable.loaded_cart);
            ttl.setText(word);
        }catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }

    public void onClickViewCart(View view) {
        startActivity(new Intent(ProductDetails.this, ViewCart.class));
    }

    private void getCheckCartCount() {
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

            }

            CART_COUNT =cursor.getCount();
            //String word = String.valueOf(cout+1);
            ttl.setText(String.valueOf(CART_COUNT));
            if (CART_COUNT>0){
                imgCartIcon.setBackgroundResource(R.drawable.loaded_cart);
            }else{
                imgCartIcon.setBackgroundResource(R.drawable.empty_cart);
            }

        }catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }

    public void onClickSavedProduct(View view) {
        String name = tvName.getText().toString();
        String quantity = tvQuantity.getText().toString();
        String price = tvNewPrice.getText().toString();
        String cost = tvCost.getText().toString();

        CODE = db.searchProductCode(String.valueOf(PRODUCT));

        //new SuperClass().setMessage(ProductDetails.this, CODE);
        if (CODE.length()<=0 && check){

            boolean isInserted = db.savedProduct(new Cart(String.valueOf(PRODUCT),name,price,quantity,cost,new SuperClass().getImageData(new SuperClass().convertImageViewToBitmap(imgIcon))));

            //new SuperClass().setMessage(ProductDetails.this, getImageData(convertImageViewToBitmap(imgIcon)));

            if (isInserted){
                Toast.makeText(ProductDetails.this, "Saved!", Toast.LENGTH_LONG).show();
                imgSavedIcon.setImageResource(R.drawable.full_saved);
                check = false;

            }else {
                Toast.makeText(ProductDetails.this, "Error!", Toast.LENGTH_LONG).show();
            }

        }else if (CODE.length()>0 || !check) {
            db.deletedSavedProduct(String.valueOf(PRODUCT));
            imgSavedIcon.setImageResource(R.drawable.empty_saved);
            check = true;
        }

    }

    public void setSavedProductIcon(){
        if (CODE.equals(String.valueOf(PRODUCT))){
            imgSavedIcon.setImageResource(R.drawable.full_saved);
        }else {

            imgSavedIcon.setImageResource(R.drawable.empty_saved);
        }
    }

    public void onClickBuyNow(View view) {

        Intent intent = new Intent(getApplicationContext(), ChoosePayMethod.class);
        intent.putExtra("cost", Double.parseDouble(tvCost.getText().toString()));
        startActivity(intent);

    }

    public void onReadMore(View view) {
        new SuperClass().setMessage(this, "Feature Still Under Construction!!!");
    }
}

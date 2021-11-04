package com.dawcyn.africdecoration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductLargeImage extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    int PRODUCT;
    String NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_large_image);

        PRODUCT = getIntent().getIntExtra("image", -1);
        NAME = getIntent().getStringExtra("name");

        imageView = (ImageView) findViewById(R.id.imgProductIcon);
        textView = (TextView) findViewById(R.id.tvProductName);

        imageView.setImageResource(PRODUCT);
        textView.setText(NAME);

    }

    public void onClickEditProduct(View view) {
        Intent callIntent = new Intent(ProductLargeImage.this, EditProduct.class);
        callIntent.putExtra("name", NAME);
        callIntent.putExtra("product", PRODUCT);
        startActivity(callIntent);
    }
}

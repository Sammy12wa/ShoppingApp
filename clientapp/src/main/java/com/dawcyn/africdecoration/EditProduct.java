package com.dawcyn.africdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProduct extends AppCompatActivity {
    int productIMAGE;
    String NAME;
    ImageView imageView;
    EditText etProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        productIMAGE = getIntent().getIntExtra("product", -1);
        NAME = getIntent().getStringExtra("name");

        imageView = (ImageView) findViewById(R.id.imgProductIcon);
        etProductName = (EditText) findViewById(R.id.etProductName);

        imageView.setImageResource(productIMAGE);
        etProductName.setText(NAME);
    }

    public void onClickSaveProductDetails(View view) {
    }
}

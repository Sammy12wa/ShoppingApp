package com.dawcyn.common;

/*
 * Created by DAWCYN on 7/22/2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.dawcyn.common.Constants.FIFTH_COLUMN;
import static com.dawcyn.common.Constants.FIRST_COLUMN;
import static com.dawcyn.common.Constants.FORTH_COLUMN;
import static com.dawcyn.common.Constants.SECOND_COLUMN;
import static com.dawcyn.common.Constants.THIRD_COLUMN;


public class CartAdapter extends BaseAdapter {

  public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtForth;
    ImageView imageView;
    Cart cart = new Cart();

    public CartAdapter(Activity activity, ArrayList<HashMap<String,String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();
        if(view ==null){
           view = inflater.inflate(R.layout.cart_screen_model, null);

            txtFirst =(TextView) view.findViewById(R.id.tvProductName);
            txtSecond = (TextView) view.findViewById(R.id.tvQuantity);
            txtThird = (TextView) view.findViewById(R.id.tvPrice);
            txtForth = (TextView) view.findViewById(R.id.tvsubTotal);
            imageView = (ImageView) view.findViewById(R.id.imgProductIcon);
        }

        HashMap<String, String> map = list.get(i);
        txtFirst.setText(map.get(FIRST_COLUMN));
        txtSecond.setText(map.get(SECOND_COLUMN));
        txtThird.setText(map.get(THIRD_COLUMN));
        txtForth.setText(map.get(FORTH_COLUMN));
        imageView.setImageBitmap(new SuperClass().returnBitmapImage(map.get(FIFTH_COLUMN)));

        return view;
    }
}

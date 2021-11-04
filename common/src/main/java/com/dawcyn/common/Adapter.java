package com.dawcyn.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * Created by DAWCYN on 3/19/2017.
 */


public class Adapter extends ArrayAdapter<String> {

    int[] imgs = {};
    String[] name = {};
    double[] oldPrice = {};
    double[] discount = {};
    Context c;
    LayoutInflater inflater;

    double oldp, newp, disc;

    public Adapter(Context context, String[] name, double[] oldPrice, double[] discount, int[] imgs) {
        super(context, R.layout.store_screen, name);

        this.c= context;
        this.name = name;
        this.oldPrice= oldPrice;
        this.discount = discount;
        this.imgs = imgs;
    }

    public class ViewHolder{
        ImageView imgIcon;
        TextView txtNames;
        TextView txtOldPrice;
        TextView txtDiscount;
        TextView txtNewPrice;
        TextView tvpercent;
        FrameLayout panel;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.store_screen, null);
        }

        //Our ViewHolder Object
        final ViewHolder holder = new ViewHolder();

        //Initialize our views
        holder.imgIcon = (ImageView) convertView.findViewById(R.id.imgProductIcon);
        holder.txtNames = (TextView) convertView.findViewById(R.id.txtProductName);
        holder.txtOldPrice = (TextView) convertView.findViewById(R.id.txtOldPrice);
        holder.txtDiscount = (TextView) convertView.findViewById(R.id.txtDiscount);
        holder.txtNewPrice = (TextView) convertView.findViewById(R.id.txtNewPrice);
        holder.tvpercent = (TextView) convertView.findViewById(R.id.tvpercent);
        holder.panel = (FrameLayout) convertView.findViewById(R.id.panel_Oldprice);

        //Assign Data
        holder.imgIcon.setImageResource(imgs[position]);
        holder.txtNames.setText(name[position]);
        disc = discount[position]/100;
        oldp = oldPrice[position];
        newp = oldp - (oldp * disc);
        if (discount[position]>0){
            holder.txtDiscount.setText(String.valueOf(discount[position]).substring(0,3));
            holder.tvpercent.setVisibility(View.VISIBLE);
            holder.panel.setVisibility(View.VISIBLE);
        }else {
            holder.txtDiscount.setText(R.string.new_tag);
            holder.tvpercent.setVisibility(View.INVISIBLE);
            holder.panel.setVisibility(View.INVISIBLE);
        }
        holder.txtOldPrice.setText(String.valueOf(oldPrice[position]));
        holder.txtNewPrice.setText(String.valueOf(newp).trim());



        return convertView;
    }
}

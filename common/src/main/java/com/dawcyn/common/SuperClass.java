package com.dawcyn.common;

/*
 * Created by DAWCYN on 8/4/2018.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ViewFlipper;


import java.io.ByteArrayOutputStream;

public class SuperClass {

    public int cartCount = 0;
    public Bitmap theImage;
    Context context;



    int[] images= {R.drawable.slider11a, R.drawable.slider2, R.drawable.slider3,
            R.drawable.slider4, R.drawable.slider5, R.drawable.slider6,
            R.drawable.slider7, R.drawable.slider8, R.drawable.slider9,
            R.drawable.slider10, R.drawable.slider1};

   public int[] products = {R.drawable.style6,R.drawable.style5,R.drawable.style4,R.drawable.style3,
                            R.drawable.style2,R.drawable.style1};
    public double[] oldPrice = {200,150,100,300,450,50};
    public double[] discount = {15,20,30,10,40,50};
    public String[] name = {"Style 1","Style 2","Style 3","Style 4","Style 5","Style 6"};


    public int[] shirts = {R.drawable.tshirt1,R.drawable.tshirt2,R.drawable.tshirt3,R.drawable.tshirt4,R.drawable.tshirt5,R.drawable.tshirt6,
            R.drawable.tshirt7};
    public double[] shirt_old_price = {100,120,90,50,200,350,150};
    public double[] shirt_discount = {10,0,0,0,20,35,50};
    public String[] shirt_name = {"Funeral Flyer","One Arm",
                            "One Arm Mid", "Cute Triangle",
                            "Bottom Flyer","Africa Map","Diagonal"};

    public int[] jeans = {R.drawable.jeans1,R.drawable.jeans2,R.drawable.jeans3,R.drawable.jeans4,R.drawable.jeans5,R.drawable.jeans6,
            R.drawable.jeans7};
    public double[] jeans_old_price = {100,120,90,50,200,350,150};
    public double[] jeans_discount = {10,0,0,0,20,35,50};
    public String[] jeans_name = {"Funeral Flyer","One Arm",
            "One Arm Mid", "Cute Triangle",
            "Bottom Flyer","Africa Map","Diagonal"};

    public int[] shoes = {R.drawable.shoe1,R.drawable.shoe2,R.drawable.shoe3,R.drawable.shoe4,R.drawable.shoe5,R.drawable.shoe6,
            R.drawable.shoe7,R.drawable.shoe8,R.drawable.shoe9,R.drawable.shoe10,R.drawable.shoe12,R.drawable.shoe13,R.drawable.shoe14};
    public double[] shoes_old_price = {100,120,90,50,200,350,150,120,90,50,200,350,150};
    public double[] shoes_discount = {10,0,0,0,20,35,50,0,0,0,20,35,50};
    public String[] shoes_name = {"Shoe 1","Shoe 2",
            "Shoe 3", "Shoe 4",
            "Shoe 5","Shoe 6","Shoe 7","Shoe 8",
            "Shoe 9", "Shoe 10",
            "Shoe 11","Shoe 12","Shoe 13"};

    public int[] slippers = {R.drawable.slipper1,R.drawable.slipper2,R.drawable.slipper3,R.drawable.slipper4,R.drawable.slipper5};
    public  double[] slippers_old_price = {100,150,200,300,250};
    public double[] slippers_discount = {10,15,0,20,30};
    public String[] slippers_name = {"All Kente","Slipper 2","Slipper 3","Slipper 4","Slipper 5"};

    public int[] bags = {R.drawable.bag1,R.drawable.bag2,R.drawable.bag3,R.drawable.bag5,R.drawable.bag6};
    public double[] bags_old_price = {50,120,70,150,200};
    public double[] bags_discount = {5,0,5,15,25};
    public String[] bags_name = {"Bag 1","Bag 2","Bag 3","Bag 4","Bag 5"};


   public int[] designs = {R.drawable.design1,R.drawable.design2,R.drawable.design8,R.drawable.design9,R.drawable.design10,
                        R.drawable.design11};
   public String[] design_name = {"Design 1","Design 2","Design 3","Design 4","Design 5","Design 6"};

    public void flipperImages(Context context, int image, ViewFlipper v_flipper){
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(10000);
        v_flipper.setAutoStart(true);

       /* if (k==images.length-1){
            if (check){
                check = false;
                //count = count - 1;
                if (count == 0){
                    check = true;
                }
            }
            count = count - 1;
        }*/
        //count = count - 1;
        /*if (str.equals("Yes")){
            v_flipper.setInAnimation(context, android.R.anim.slide_in_left);
            v_flipper.setOutAnimation(context, android.R.anim.slide_out_right);
        }else if (str.equals("No")){*/
            v_flipper.setInAnimation(context, android.R.anim.fade_in);
            v_flipper.setOutAnimation(context, android.R.anim.fade_out);
       // }
    }


    public  boolean setListViewHeightBasedOnItems(ListView listView){

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null){
            int numberOfItems = listAdapter.getCount();

            int totalItemsHeight = 0;
            for (int itemsPos = 0; itemsPos < numberOfItems; itemsPos++){
                View item = listAdapter.getView(itemsPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            int totalDividerHeight = listView.getDividerHeight() * (numberOfItems - 1);

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividerHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();
            return  true;
        }else {
            return false;
        }
    }


    public void setMessage(Context ctx, String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setMessage(message);
        dialog.setTitle(R.string.app_name);
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    public void setOptionMessage(Context ctx, String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setMessage(message);
        dialog.setTitle(R.string.app_name);
        dialog.setPositiveButton("OK", (DialogInterface.OnClickListener) dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
            }
        }));
        dialog.setNegativeButton("Cancel", (DialogInterface.OnClickListener) dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
            }
        }));
        dialog.show();
    }

    public Bitmap convertImageString(String strImage){
        //Cart cart = new Cart();
        byte[] outImage= Base64.decode(strImage.getBytes(), Base64.DEFAULT);
        //ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
         theImage = BitmapFactory.decodeByteArray(outImage, 0, outImage.length);
        return theImage;
    }

    public String getImageData(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }

    public Bitmap returnBitmapImage(String image){
        byte[] decodedString = Base64.decode(image.getBytes(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public Bitmap convertImageViewToBitmap(ImageView v){

        Bitmap bm=((BitmapDrawable)v.getDrawable()).getBitmap();

        return bm;
    }

    public void gatewayOptions(RadioButton[] radioButton,RadioButton btn, String str){
      try{
          int clickedID = btn.getId();

          for (int i = 0; i<= radioButton.length; i++){
              int getId = radioButton[i].getId();
              if (getId == clickedID ){
                  str = radioButton[i].getText().toString();
                  radioButton[i].setChecked(true);
              }else {
                  radioButton[i].setChecked(false);
              }
          }
      }catch (Exception ex){
          Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show();
      }

    }

}

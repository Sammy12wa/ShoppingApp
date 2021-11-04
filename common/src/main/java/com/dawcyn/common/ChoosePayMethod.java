package com.dawcyn.common;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ChoosePayMethod extends AppCompatActivity {
    Double COST;
    TextView tvCost;
    RadioButton btnCash,btnPaypal,btnMoMo,btnBank,btnCheque;

    SuperClass callClass = new SuperClass();

    RadioButton[] radioButtons;
    String strOptions = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pay_method);

        COST = getIntent().getDoubleExtra("cost", 0);

        tvCost = (TextView) findViewById(R.id.tvNetTotal);
        tvCost.setText(String.valueOf(COST));

        btnCash = (RadioButton) findViewById(R.id.rbtnCashOnDelivery);
        btnPaypal = (RadioButton) findViewById(R.id.rbtnPayPal);
        btnMoMo = (RadioButton) findViewById(R.id.rbtnMoMo);
        btnBank = (RadioButton) findViewById(R.id.rbtnBankTransfer);
        btnCheque = (RadioButton) findViewById(R.id.rbtnCheque);

        radioButtons = new RadioButton[] {btnCash,btnPaypal,btnMoMo,btnBank,btnCheque};

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callClass.gatewayOptions(radioButtons,btnCash,strOptions);
                btnCash.setChecked(true);
                btnPaypal.setChecked(false);
                btnMoMo.setChecked(false);
                btnBank.setChecked(false);
                btnCheque.setChecked(false);
                strOptions = btnCash.getText().toString();
            }
        });
        btnPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callClass.gatewayOptions(radioButtons,btnPaypal,strOptions);
                btnCash.setChecked(false);
                btnPaypal.setChecked(true);
                btnMoMo.setChecked(false);
                btnBank.setChecked(false);
                btnCheque.setChecked(false);
                strOptions = btnPaypal.getText().toString();
            }
        });
        btnMoMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCash.setChecked(false);
                btnPaypal.setChecked(false);
                btnMoMo.setChecked(true);
                btnBank.setChecked(false);
                btnCheque.setChecked(false);
                strOptions = btnMoMo.getText().toString();
            }
        });
        btnBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCash.setChecked(false);
                btnPaypal.setChecked(false);
                btnMoMo.setChecked(false);
                btnBank.setChecked(true);
                btnCheque.setChecked(false);
                strOptions = btnBank.getText().toString();
            }
        });
        btnCheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCash.setChecked(false);
                btnPaypal.setChecked(false);
                btnMoMo.setChecked(false);
                btnBank.setChecked(false);
                btnCheque.setChecked(true);
                strOptions = btnCheque.getText().toString();
            }
        });

    }

    public void onClickPayMethodsBack(View view) {finish();
    }

    public void onClickProceedNow(View view) {
     if (strOptions.equals("")){
         Toast.makeText(ChoosePayMethod.this, "Choose at least One Payment Option!", Toast.LENGTH_LONG).show();
     }else {
         double cost = Double.parseDouble(tvCost.getText().toString());
         //callClass.setMessage(getApplicationContext(), strOptions);
         callClass.setMessage(ChoosePayMethod.this, "Your payment of GH¢ "+cost
                 + " to African Shoppee Successful!\nPayment Gateway: "+strOptions+
                 " \n You'll be called for delivery details by our Delivery Agent!");
         //Toast.makeText(getApplicationContext(), strOptions, Toast.LENGTH_LONG).show();
         //finish();
     }
    }
}

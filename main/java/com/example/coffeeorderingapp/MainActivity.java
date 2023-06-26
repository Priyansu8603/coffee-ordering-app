package com.example.coffeeorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void displayQuantity(int noOfCoffees){
        TextView quantityView =  (TextView) findViewById(R.id.quantity_text_view);
        quantityView.setText(""+ noOfCoffees);
    }
//    private void displaySummary(String summary){
//
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(summary);
//
//    }


    public void incrementQuantity(View v){
        if (quantity>100){
            Toast.makeText(this,"You Cannot Order More Than 100 Coffees ",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    public void decrementQuantity(View view) {
        if (quantity<1){
            Toast.makeText(this,"You Cannot Order Less Than 1 Coffees ",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);

    }




    private String summary(int quantity,boolean Chocolate, boolean WhippedCream, String name) {
        String priceMessage = "Name : "+name;
        priceMessage += "\nWhippedCream ?"+WhippedCream;
        priceMessage += "\nChocolate ?"+Chocolate;
        priceMessage += "\nQuantity : "+ quantity;
        priceMessage += "\nTotal: $ "+ calculatePrice(quantity,WhippedCream,Chocolate);
        priceMessage += "\nThankU!";
        return priceMessage;
    }

    private int calculatePrice(int quantity,boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        if (hasWhippedCream){
            basePrice += 1;
        }
        if (hasChocolate){
            basePrice += 2;
        }
        return basePrice*quantity;
    }



    public void orderSummary(View view) {

        EditText nameInput = (EditText)findViewById(R.id.name_text_view);
         String name = nameInput.getText().toString();

        CheckBox WhippedCream = (CheckBox) findViewById(R.id.whipped_check_box);
         boolean hasWhippedCream = WhippedCream.isChecked();

        CheckBox Chocolate = (CheckBox) findViewById(R.id.chocolate_check_box);
         boolean hasChocolate = Chocolate.isChecked();

        String Message = summary(quantity,hasChocolate,hasWhippedCream,name);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,"priyanshukumar238202@gmail.com");
        intent.setType("*/*");
       // intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT , "Coffee Order For"+ name);
        intent.putExtra(Intent.EXTRA_TEXT ,Message);
        if (intent.resolveActivity(getPackageManager())!=null){
        startActivity(Intent.createChooser(intent,"Send Email....."));

        //displaySummary(summary(quantity,hasChocolate,hasWhippedCream,name));

        }}
    }


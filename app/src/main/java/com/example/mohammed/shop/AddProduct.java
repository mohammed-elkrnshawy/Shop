package com.example.mohammed.shop;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddProduct extends Activity {

    EditText name,code,price,quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_add_product);
        Database.CreateDatabase(this);
        Database.CreateTableProduct("product",this);
    }
    public void addclick(View view)
    {
        name=(EditText)findViewById(R.id.tb_name);
        code=(EditText)findViewById(R.id.tb_code);
        price=(EditText)findViewById(R.id.tb_price);
        quantity=(EditText)findViewById(R.id.tb_quantity);
        Database.InsertProduct("product",name.getText().toString(),code.getText().toString(),price.getText().toString(),Integer.parseInt(quantity.getText().toString()),this);
        Database.RetriveProduct(this);
    }
}

package com.example.mohammed.shop;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText Username,Password,Phone;
    ImageView BillClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        BillClick=(ImageView)findViewById(R.id.BillClick);
        Database.CreateDatabase(this);
        Database.CreateTableUser("User");





    }
    public void BillClick(View view)
    {
        Intent k = new Intent(MainActivity.this, BillMain.class);
        startActivity(k);
    }
    public void SignUp(View view)
    {
        setContentView(R.layout.sign_up);

    }
    public void SignupSignup(View view)
    {
        Username=(EditText)findViewById(R.id.txt_user_signup);
        Password=(EditText)findViewById(R.id.txt_password_signup);
        Phone=(EditText)findViewById(R.id.txt_phone_signup);
        Database.InsertUser("User",""+Username.getText(),""+Password.getText(),""+Phone.getText(),this);
    }

    public void SignIn(View view)
    {

        Username=(EditText)findViewById(R.id.txt_user);
        Password=(EditText)findViewById(R.id.txt_password);
        if (Username.getText().toString().equals("Admin")&&Password.getText().toString().equals("Admin"))
        {
            setContentView(R.layout.admin_main);
        }
        else
        {
            Boolean result= Database.SearchUser("User",Username.getText().toString(),Password.getText().toString(),this);

            if(result){
                Parameter.UserName=Username.getText().toString();
                setContentView(R.layout.main);
            }
            else
            {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ProductClick(View view) {
        try {
            Intent intent = new Intent(this, AddProduct.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void ReportClick(View view)
    {
        Intent intent=new Intent(this,AdminReport.class);
        startActivity(intent);
    }

}

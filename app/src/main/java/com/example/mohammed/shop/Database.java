package com.example.mohammed.shop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;



public class Database {
    static SQLiteDatabase myDB;
    static String Data = "";

    public static void CreateDatabase(Context context) {
        try {
            myDB = context.openOrCreateDatabase("Stock", MODE_PRIVATE, null);

        } catch (Exception ex) {
            Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
        }
    }

    public static void CreateTableUser(String TableName) {
        try {


            myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                    + TableName
                    + " (UserName VARCHAR, PassWord VARCHAR, Phone VARCHAR);");
        } catch (Exception exc) {


        }

    }

    public static void InsertUser(String TableName, String Username, String Password, String Phone, Context context) {
        try {
            myDB.execSQL("INSERT INTO "
                    + TableName
                    + " (UserName, Password, Phone)"
                    + " VALUES ('" + Username + "', '" + Password + "','" + Phone + "');");
            Toast.makeText(context, "Seccesful Insert", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {

            Toast.makeText(context, "Fail Insert", Toast.LENGTH_SHORT).show();
        }
    }

    public static Boolean SearchUser(String TableName, String Username, String Password,Context context) {
        Boolean r=false;
        /*retrieve data from database */
        try {

            myDB = context.openOrCreateDatabase("Stock", MODE_PRIVATE, null);
            Cursor c = myDB.rawQuery("SELECT * FROM 'User' WHERE UserName='" + Username + "'AND Password='" + Password + "' ", null);
            c.moveToFirst();
            c.getCount();

            /*int Column1 = c.getColumnIndex("Field1");
            int Column2 = c.getColumnIndex("Field2");*/

            // Check if our result was valid.
            // c.moveToFirst();
            if (c.getCount()>0) {
                // Loop through all Results
                /*do {
                    String Name = c.getString(Column1);
                    int Age = c.getInt(Column2);
                    Data = Data + Name + "/" + Age + "\n";
                } while (c.moveToNext());*/
                // Toast.makeText(context, "sec", Toast.LENGTH_SHORT).show();
                r=true;
            }
            else
            {
                r=false;
            }
        } catch (Exception e) {

        }
        return r;
    }

    public static void CreateTableProduct(String TableName,Context context)
    {
        try {


            myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                    + TableName
                    + " (Name VARCHAR, Code VARCHAR, Price VARCHAR,Quantity INT);");
        } catch (Exception exc) {


        }

    }

    public static void InsertProduct(String TableName, String Name, String Code, String Price,int quan, Context context) {
        try {
            myDB.execSQL("INSERT INTO "
                    + TableName
                    + " (Name, Code, Price, Quantity)"
                    + " VALUES ('" + Name + "', '" + Code + "','" + Price + "','" + quan + "');");
            Toast.makeText(context, "Seccesful Insert", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static ArrayList<ProductDetails> RetriveProduct(Context context) {
        ArrayList<ProductDetails>arrayList=new ArrayList<>();
        try {
            myDB = context.openOrCreateDatabase("Stock", MODE_PRIVATE, null);
            String query = "SELECT * FROM 'product'";
            Cursor cursor = myDB.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    String name=cursor.getString(0);
                    String code=cursor.getString(1);
                    String price=cursor.getString(2);
                    arrayList.add(new ProductDetails(name,code,price));
                } while (cursor.moveToNext());
            }
            Toast.makeText(context, "Retrive Sucssufl", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(context,ex.getMessage() , Toast.LENGTH_LONG).show();
        }
        return arrayList;
    }
    public static void CreateTableBill(String TableName,Context context)
    {
        try {


            myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                    + TableName
                    + " (BillNumber VARCHAR, CustomerName VARCHAR, DateBill VARCHAR, TotalBill VARCHAR);");
        } catch (Exception exc) {


        }
    }
    public static void InsertBill(String TableName, String BillNumber, String CustomerName, String DateBill, String TotalBill,Context context) {
        try {
            myDB.execSQL("INSERT INTO "
                    + TableName
                    + " (BillNumber, CustomerName, DateBill, TotalBill)"
                    + " VALUES ('" + BillNumber + "', '" + CustomerName + "','" + DateBill + "','" + TotalBill + "');");
            Toast.makeText(context, "Seccesful Insert", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {

            Toast.makeText(context, "Fail Insert", Toast.LENGTH_SHORT).show();
        }
    }

    public static ArrayList<String> RetriveBill(Context context) {
        ArrayList<String>arrayList=new ArrayList<>();
        try {
            myDB = context.openOrCreateDatabase("Stock", MODE_PRIVATE, null);
            String query = "SELECT * FROM 'Bill'";
            Cursor cursor = myDB.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    String BillNumber=cursor.getString(0);
                    String CustomerName=cursor.getString(1);
                    String DateBill=cursor.getString(2);
                    String TotalBill=cursor.getString(3);
                    arrayList.add("Bill Number   : "+BillNumber+"\n"+
                                  "Customer Name : "+CustomerName+"\n"+
                                  "Bill DAte     : "+DateBill+"\n"+
                                  "Bill Total    : "+TotalBill+"\n"
                    );
                } while (cursor.moveToNext());
            }
            Toast.makeText(context, "Retrive Sucssufl", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(context,ex.getMessage() , Toast.LENGTH_LONG).show();
        }
        return arrayList;
    }
}

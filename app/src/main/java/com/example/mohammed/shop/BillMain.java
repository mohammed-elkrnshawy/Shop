package com.example.mohammed.shop;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BillMain extends Activity {

    ArrayList<ProductDetails>arrayList;
    int ooo=1;
    double sum=0;
    TextView Wellcome,TotalPrice,AfterDiscount;
    ListView listView;
    EditText number,Discount,customerName,billDate,billNumber;
    TableLayout tbl;
    public void AddRow() {

        double Dis=Double.parseDouble(Discount.getText()+"");
        TableRow newRow = new TableRow(this);

        TextView textView = new TextView(this);
        textView.setText(""+ooo);
        newRow.addView(textView);

        TextView textView1 = new TextView(this);
        textView1.setText(Parameter.Code);
        newRow.addView(textView1);

        TextView textView2 = new TextView(this);
        textView2.setText(Parameter.ProductName);
        newRow.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setText("Units");
        newRow.addView(textView3);

        TextView textView4 = new TextView(this);
        textView4.setText(Parameter.Price);
        newRow.addView(textView4);

        TextView textView5 = new TextView(this);
        textView5.setText(Parameter.Quantity);
        newRow.addView(textView5);

        TextView textView6 = new TextView(this);
        textView6.setText(Parameter.Total);
        newRow.addView(textView6);



        tbl.addView(newRow);
        sum+=Double.parseDouble(Parameter.Total);
        ooo++;
        TotalPrice.setText(sum+"");
        AfterDiscount.setText(""+(sum-Dis));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_bill_main);
        Wellcome = (TextView) findViewById(R.id.txt_wellcome);
        Wellcome.setText("Wllcome " + Parameter.UserName);
        arrayList= Database.RetriveProduct(BillMain.this);
        tbl = (TableLayout) findViewById(R.id.Table);
        listView = (ListView) findViewById(R.id.list);
        TotalPrice=(TextView)findViewById(R.id.total_price);
        AfterDiscount=(TextView)findViewById(R.id.textView9);
        Discount=(EditText)findViewById(R.id.discountb);
        customerName=(EditText) findViewById(R.id.CustomerName);
        billDate=(EditText) findViewById(R.id.BillDate);
        billNumber=(EditText) findViewById(R.id.BillNumber);


        ArrayList<ProductDetails> values = new ArrayList<>();
        values = Database.RetriveProduct(this);
       final ArrayList<String> jj=new ArrayList<>();
        for(int i=0;i<values.size();i++)
        {
            jj.add(values.get(i).name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,jj);


        listView.setAdapter(adapter);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {








                        final String itemValue = (String) listView.getItemAtPosition(position);


                            LayoutInflater factory = LayoutInflater.from(BillMain.this);
                            final View deleteDialogView = factory.inflate(R.layout.popup_number, null);
                            final AlertDialog deleteDialog = new AlertDialog.Builder(BillMain.this).create();
                            deleteDialog.setView(deleteDialogView);
                            deleteDialogView.findViewById(R.id.bt_select).setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v) {



                                    try{
                                        number=(EditText)deleteDialog.findViewById(R.id.numbertryu);

                                        int index= jj.indexOf(itemValue);

                                        Parameter.Quantity=number.getText().toString();
                                        Parameter.ProductName=itemValue;
                                        Parameter.Code=arrayList.get(index).code;
                                        Parameter.Price=arrayList.get(index).price;
                                        Parameter.Total=(""+(Double.parseDouble(Parameter.Quantity))*(Double.parseDouble(Parameter.Price)));
                                        deleteDialog.dismiss();
                                        AddRow();
                                    }catch (Exception ex)
                                    {
                                        Toast.makeText(BillMain.this,ex.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            deleteDialog.show();

                    }


                });
    }

    public void SaveClick(View view)
    {
        Database.CreateTableBill("Bill",this);
        Database.InsertBill("Bill",billNumber.getText()+"",customerName.getText()+"",billDate.getText()+"",AfterDiscount.getText()+"",this);
    }

}
package com.example.ema.traint_ag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private TextView myname;
    private TextView mymoney;
    private TextView myemail;
    private Button home;
    private Button favlabel;
    private Button moneylabel;
    private Button moneybtn;
    private ListView favlist;
    private EditText transfermoney;
    private RelativeLayout moneylay;
    private ArrayAdapter<String> adapter;
    private Searching searching = new Searching();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myname = (TextView) findViewById(R.id.myname);
        mymoney = (TextView) findViewById(R.id.mymoney);
        myemail = (TextView) findViewById(R.id.mymail);
        home = (Button) findViewById(R.id.myhome);
        favlabel = (Button) findViewById(R.id.favv);
        moneylabel = (Button) findViewById(R.id.moneyy);
        moneybtn = (Button) findViewById(R.id.moneybtn);
        favlist = (ListView) findViewById(R.id.favlist);
        transfermoney = (EditText) findViewById(R.id.moneytext);
        moneylay = (RelativeLayout) findViewById(R.id.moneylay);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
         final int money = getIntent().getIntExtra("money", 0);
        final int id = getIntent().getIntExtra("id", 0);
        myname.setText(name);
        mymoney.setText("" + money);
        myemail.setText(email);

        favlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneylay.setVisibility(View.INVISIBLE);
                favlist.setVisibility(View.VISIBLE);
            }
        });
        moneylabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneylay.setVisibility(View.VISIBLE);
                favlist.setVisibility(View.INVISIBLE);
            }
        });
        final SQLiteDatabase sql=openOrCreateDatabase("prof", MODE_PRIVATE, null);

        moneybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trans = transfermoney.getText().toString();
                int transmoney = 0;
                try {
                    Cursor c=sql.rawQuery("select * from profiles where ID="+id, null);
                    int money=0;
                    while (c.moveToNext()) {
                        int moneyindex = c.getColumnIndexOrThrow("MONEY");
                        money = c.getInt(moneyindex);
                    }

                    transmoney = Integer.parseInt(trans);
                    SQLiteDatabase sql = openOrCreateDatabase("prof", MODE_PRIVATE, null);
                    int newmoney = money + transmoney;
                    sql.execSQL("update profiles set MONEY=" + newmoney + " where id=" + id);
                    sql.close();
                    mymoney.setText("" + newmoney);
                } catch (Exception e) {
                    Toast.makeText(Profile.this, "This not number only number ,Sir.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Cursor cursor=sql.rawQuery("select * from Booked where ID="+id+";", null);
        ArrayList<String> arr = new ArrayList<String>();
        while(cursor.moveToNext()){
            arr.add(cursor.getString(1));
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arr) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);
                return view;
            }
        };
        favlist.setAdapter(adapter);


        favlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long iid) {
                  final SQLiteDatabase profiles = openOrCreateDatabase("prof", MODE_PRIVATE, null);

               int id=getSharedPreferences("Data",MODE_PRIVATE).getInt("ID", 0);
                String num="0";
                if(parent.getItemAtPosition(position).toString().charAt(1)!=' ')
                    num=""+parent.getItemAtPosition(position).toString().charAt(0)+parent.getItemAtPosition(position).toString().charAt(1);
                else num=""+parent.getItemAtPosition(position).toString().charAt(0);
                int number=Integer.parseInt(num);

                final Cursor c=profiles.rawQuery("select * from profiles where ID="+id, null);
                int money=0;
                while (c.moveToNext()) {
                    int moneyindex = c.getColumnIndexOrThrow("MONEY");
                    money = c.getInt(moneyindex);
                }
                int sum = money + number;

                try {
                    Toast.makeText(Profile.this,parent.getItemAtPosition(position).toString() , Toast.LENGTH_SHORT).show();
                    profiles.execSQL("delete from Booked where ID="+id+" and bookdata = \'" + parent.getItemAtPosition(position) + "\';");
                    profiles.execSQL("update profiles set MONEY=" + sum + " where ID = " + id + ";");
                    mymoney.setText("" + sum);
                    Toast.makeText(getApplicationContext(), "Done removed from Booked list", Toast.LENGTH_SHORT).show();
                    favlist.refreshDrawableState();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

    });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Searching.class));
                finish();
            }
        });

    }




}

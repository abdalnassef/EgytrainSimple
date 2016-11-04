package com.example.ema.traint_ag;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Forgot_pass extends AppCompatActivity {

    private EditText Email;
    private Button Send;
    private ImageView Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        Email=(EditText)findViewById(R.id.email);
        Send=(Button)findViewById(R.id.send);
        Home=(ImageView)findViewById(R.id.home);


        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
String email=Email.getText().toString();
                if(email.equals("")) Toast.makeText(getApplication(),"Enter your email first",Toast.LENGTH_SHORT).show();
                else{
                    SQLiteDatabase db=openOrCreateDatabase("prof",MODE_PRIVATE,null);
                    Cursor c=db.rawQuery("select EMAIL from profiles",null);
                    while(c.moveToNext()){
                        int index=c.getColumnIndex("EMAIL");
                        if(email.equals(c.getString(index).toString())){
                            Intent i =new Intent(Forgot_pass.this,wait_for_activate.class);
                            i.putExtra("RepassEmail",email);
                            startActivity(i);
                            finish();
                        }
                    }
                }
            }
        });
    }
}

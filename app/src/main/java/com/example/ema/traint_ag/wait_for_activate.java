package com.example.ema.traint_ag;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class wait_for_activate extends AppCompatActivity {

    private TextView Activate;
    private TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_for_activate);

        Intent x=getIntent();
        final String email=x.getStringExtra("RepassEmail");

        Activate=(TextView)findViewById(R.id.assume);
        password=(TextView)findViewById(R.id.yourpass);
        Activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=openOrCreateDatabase("prof",MODE_PRIVATE,null);
                final Cursor c=db.rawQuery("select * from profiles", null);

                while (c.moveToNext()) {
                    int passindex = c.getColumnIndexOrThrow("PASSWORD");
                    int emailindex = c.getColumnIndexOrThrow("EMAIL");

                    String passs = c.getString(passindex);
                    String emaill = c.getString(emailindex);
                    if(emaill.equals(email)){
                        password.setText(passs);
                    }
                }
            }
        });
    }
}

package com.example.ema.traint_ag;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText Email;
    private EditText Name;
    private EditText Pass;
    private EditText PassCF;
    private EditText Money;
    private Button SignUp;
    private TextView SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Email=(EditText)findViewById(R.id.email);
        Name=(EditText)findViewById(R.id.name);
        Pass=(EditText)findViewById(R.id.pass);
        PassCF=(EditText)findViewById(R.id.passcf);
        Money=(EditText)findViewById(R.id.money);
        SignUp=(Button)findViewById(R.id.signup);
        SignIn=(TextView)findViewById(R.id.signin);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUp.this,login.class);
                startActivity(i);
                finish();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=Email.getText().toString();
                String username=Name.getText().toString();
                String password=Pass.getText().toString();
                String passwordcf=PassCF.getText().toString();
                String money=Money.getText().toString();
                if(!password.equals(passwordcf)){
                    Toast.makeText(getApplicationContext(),"Two passwords not matched check them ,Sir",Toast.LENGTH_SHORT).show();
                }else if(email.equals("")||username.equals("")||password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill all Fields , Sir", Toast.LENGTH_SHORT).show();
                }else{
                    if(money.equals(""))money="0";
                    try {
                         SQLiteDatabase profiles = openOrCreateDatabase("prof", MODE_PRIVATE, null);
                         try {
                            // profiles.execSQL("drop table profiles;");
                             profiles.execSQL("insert into profiles (EMAIL,NAME,PASSWORD,MONEY) values(\'" + email + "\',\'" + username + "\',\'" + password + "\'," + money + ");");
                             Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_SHORT).show();
                         }catch(Exception e){
                             profiles.execSQL("create table profiles(ID INTEGER PRIMARY KEY   AUTOINCREMENT,EMAIL TEXT NOT NULL,NAME TEXT NOT NULL,PASSWORD TEXT NOT NULL,MONEY INT NOT NULL);");
                             profiles.execSQL("insert into profiles (EMAIL,NAME,PASSWORD,MONEY) values(\'01098765112\',\'ahmed\',\'000\',200);");
                             profiles.execSQL("insert into profiles (EMAIL,NAME,PASSWORD,MONEY) values(\'01102823464\',\'ali\',\'333\',70);");
                             profiles.execSQL("insert into profiles (EMAIL,NAME,PASSWORD,MONEY) values(\'01012736452\',\'osama\',\'222\',500);");
                             profiles.execSQL("insert into profiles (EMAIL,NAME,PASSWORD,MONEY) values(\'01099982714\',\'abdalnassef\',\'111\',1000);");
                             Toast.makeText(getApplication(),"Done crated",Toast.LENGTH_SHORT).show();
                             }
                     }catch (Exception e){
                         Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                     }
                }

            }
        });

    }
}

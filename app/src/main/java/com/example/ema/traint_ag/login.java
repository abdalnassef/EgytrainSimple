package com.example.ema.traint_ag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class login extends AppCompatActivity {

    private SharedPreferences namepass;
    private Button signin;
    private EditText myname;
    private EditText mypass;
    private TextView signup;
    private TextView passforget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginx);

        try {
            FileOutputStream fos = openFileOutput("TantaToAlex.txt", MODE_PRIVATE);
            OutputStreamWriter opw=new OutputStreamWriter(fos);
            opw.close();
            fos.close();
             fos = openFileOutput("TantaToCairo.txt", MODE_PRIVATE);
             opw=new OutputStreamWriter(fos);
            opw.close();
            fos.close();
             fos = openFileOutput("TantaToMenof.txt", MODE_PRIVATE);
             opw=new OutputStreamWriter(fos);
            opw.close();
            fos.close();

            String Data="5"+"|"+"طنطا"+"|"+"الاسكندرية"+"|"+Kind.اكسبرس_مميز+"|"+"08.01"+"|"+"10.10"+"\n";
            writedata("TantaToAlex.txt",Data);
             Data="50 "+"|"+"طنطا"+"|"+"الاسكندرية"+"|"+Kind.اكسبرس_مكيف+"|"+"09.20"+"|"+"11.00"+"\n";
            writedata("TantaToAlex.txt",Data);
             Data="5  "+"|"+"طنطا"+"|"+"الاسكندرية"+"|"+Kind.اكسبرس_مميز+"|"+"10.05"+"|"+"12.10"+"\n";
            writedata("TantaToAlex.txt",Data);
             Data="7  "+"|"+"طنطا"+"|"+"الاسكندرية"+"|"+Kind.اكسبرس_مطور+"|"+"13.45"+"|"+"15.40"+"\n";
            writedata("TantaToAlex.txt",Data);
             Data="5  "+"|"+"طنطا"+"|"+"الاسكندرية"+"|"+Kind.اكسبرس_مميز+"|"+"20.12"+"|"+"22.15"+"\n";
            writedata("TantaToAlex.txt",Data);
             Data="5  "+"|"+"طنطا"+"|"+"الاسكندرية"+"|"+Kind.اكسبرس_مميز+"|"+"22.40"+"|"+"00.45"+"\n";
            writedata("TantaToAlex.txt",Data);

            Data="7  "+"|"+"طنطا"+"|"+"القاهرة"+"|"+Kind.اكسبرس_مميز+"|"+"04.29"+"|"+"06.15"+"\n";
            writedata("TantaToCairo.txt",Data);
            Data="5  "+"|"+"طنطا"+"|"+"القاهرة"+"|"+"عادى                 "+"|"+"06.55"+"|"+"10.25"+"\n";
            writedata("TantaToCairo.txt",Data);
            Data="35 "+"|"+"طنطا"+"|"+"القاهرة"+"|"+Kind.اكسبرس_مكيف+"|"+"07.15"+"|"+"8.30"+"\n";
            writedata("TantaToCairo.txt",Data);
            Data="35 "+"|"+"طنطا"+"|"+"القاهرة"+"|"+Kind.اكسبرس_مكيف+"|"+"11.42"+"|"+"12.55"+"\n";
            writedata("TantaToCairo.txt",Data);
            Data="7  "+"|"+"طنطا"+"|"+"القاهرة"+"|"+Kind.اكسبرس_مميز+"|"+"14.33"+"|"+"16.00"+"\n";
            writedata("TantaToCairo.txt",Data);
            Data="7  "+"|"+"طنطا"+"|"+"القاهرة"+"|"+Kind.اكسبرس_مميز+"|"+"15.54"+"|"+"17.45"+"\n";
            writedata("TantaToCairo.txt",Data);

            Data="5  "+"|"+"طنطا"+"|"+"منوف"+"|"+Kind.اكسبرس_مطور+"|"+"08.10"+"|"+"09.15"+"\n";
            writedata("TantaToMenof.txt",Data);
            Data="2  "+"|"+"طنطا"+"|"+"منوف"+"|"+"عادى                 "+"|"+"10.00"+"|"+"11.17"+"\n";
            writedata("TantaToMenof.txt",Data);
            Data="2  "+"|"+"طنطا"+"|"+"منوف"+"|"+"عادى                 "+"|"+"12.35"+"|"+"13.40"+"\n";
            writedata("TantaToMenof.txt",Data);
            Data="2  "+"|"+"طنطا"+"|"+"منوف"+"|"+"عادى                 "+"|"+"14.05"+"|"+"15.18"+"\n";
            writedata("TantaToMenof.txt",Data);
            Data="2  "+"|"+"طنطا"+"|"+"منوف"+"|"+"عادى                 "+"|"+"15.00"+"|"+"16.07"+"\n";
            writedata("TantaToMenof.txt",Data);

            Toast.makeText(login.this, "Done", Toast.LENGTH_SHORT).show();


        }catch (Exception e){

        }


//        try {
//            readdata("Data.txt");
//        }catch (Exception e){
//
//        }









        signin=(Button)findViewById(R.id.signin);
        signup=(TextView)findViewById(R.id.signup);
        passforget=(TextView)findViewById(R.id.passforget);
        myname=(EditText)findViewById(R.id.name);
        mypass=(EditText)findViewById(R.id.password);
        
        namepass =getSharedPreferences("Data",MODE_PRIVATE);

//        Resources res=getResources();
//        final String[] names=res.getStringArray(R.array.namearray);
//        final String[] passwords=res.getStringArray(R.array.passwordarray);
//        final int[] id=res.getIntArray(R.array.IDarray);

         final SQLiteDatabase profiles=openOrCreateDatabase("prof", MODE_PRIVATE, null);


        int shareID=namepass.getInt("ID", 0);
        if(shareID!=0){
            Intent intent=new Intent(login.this,Searching.class);
            intent.putExtra("myID", shareID);
            startActivity(intent);
            finish();
           }
        signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Cursor c=profiles.rawQuery("select * from profiles",null);
                boolean found= false,emptyfields=false;

                while (c.moveToNext()) {
                    int passindex = c.getColumnIndexOrThrow("PASSWORD");
                    int nameindex = c.getColumnIndexOrThrow("NAME");
                    int idindex = c.getColumnIndexOrThrow("ID");

                    String passs = c.getString(passindex);
                    String namee = c.getString(nameindex);
                    int idd = c.getInt(idindex);

                    if(namee.equals(myname.getText().toString().trim())&&passs.equals(mypass.getText().toString())){
                        found=true;
                        SharedPreferences.Editor edit=namepass.edit();
                        edit.putInt("ID",idd);
                        edit.commit();
                        Intent intent=new Intent(login.this,Searching.class);
                        intent.putExtra("myID", idd);
                        startActivity(intent);
                        finish();
                    }else if(myname.getText().toString().equals("")||mypass.getText().toString().equals("")){
                        found=true;
                        emptyfields=true;
                    }
                }
                if(emptyfields){
                    Toast.makeText(getApplication(),"Fill fields ,Sir",Toast.LENGTH_SHORT).show();
                }
                else if(!found){
                    Toast.makeText(getApplication(),"Password or Name wrong ,Sir",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        passforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,Forgot_pass.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public void writedata(String filename,String data) throws IOException {
        FileOutputStream fos = openFileOutput(filename, MODE_APPEND);
        OutputStreamWriter opw=new OutputStreamWriter(fos);
        opw.write(data);
        opw.close();
        fos.close();
    }


}

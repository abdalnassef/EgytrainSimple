package com.example.ema.traint_ag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Searching extends AppCompatActivity {

    private String From="";
    private String To="";
    ArrayAdapter<String> adapter;
    private info[] dat=new info[5];
    private info dat1;

    private Button logout;
//    private Button DB;
    private Button show;
    private Button myProfile;
    private Button TrainTime;
    private Button Times;
    private LinearLayout sp;
    private LinearLayout lapel;
    private Spinner Tospinner;
    private Spinner Fromspinner;
    private ListView li;
    //    private Button dell;
    private TextView showdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        /*
try {
    String Data = "";
    Data = "طنطا" + "|" + "منوف" + "|" + Kind.اكسبرس_مطور + "|" + "08.10" + "|" + "09.15" + "\n";
    writedata("TantaToMenof.txt", Data);
    Data = "طنطا" + "|" + "منوف" + "|" + "عادى                 " + "|" + "10.00" + "|" + "11.17" + "\n";
    writedata("TantaToMenof.txt", Data);
    Data = "طنطا" + "|" + "منوف" + "|" + "عادى                 " + "|" + "12.35" + "|" + "13.40" + "\n";
    writedata("TantaToMenof.txt", Data);
    Data = "طنطا" + "|" + "منوف" + "|" + "عادى                 " + "|" + "14.05" + "|" + "15.18" + "\n";
    writedata("TantaToMenof.txt", Data);
    Data = "طنطا" + "|" + "منوف" + "|" + "عادى                 " + "|" + "15.00" + "|" + "16.07" + "\n";
    writedata("TantaToMenof.txt", Data);
    Toast.makeText(Searching.this, "Done", Toast.LENGTH_SHORT).show();
}catch (Exception e){}
*/

//        if(To.equals("طنطا")) {
//            if (From.equals("لسنطة")) {
//                dat[0] = new info("03.40", "03.20", Kind.عادى, 1);
//                dat[1] = new info("05.10", "04.50", Kind.عادى, 1);
//                dat[2] = new info("14.05", "13.45", Kind.اكسبرس_مميز, 2);
//                dat[3] = new info("15.05", "14.45", Kind.عادى, 1);
//            }
//
//        }
//        final String[] arrdat = new String[1];
//        int i=0;
//        try {
//            arrdat[i] = dat[i].getTimestart().toString() + "  " + dat[i].getTimearrive().toString() + "  " + dat[0].getKind().toString() + "  " + dat[i].getSalary() + "  ";
//        }catch (Exception e){
//            Toast.makeText(Searching.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        for (int i = 0; i < dat.length; i++) {
//            String mm = dat[0].getKind().toString();
//            if (mm.equals("عادى")) mm = "عادى                 ";
//            else if (mm.equals("نوم")) mm = "نوم                    ";
//            arrdat[i] = dat[i].getTimestart().toString() + "  " + dat[i].getTimearrive().toString() + "  " + mm + "  " + dat[i].getSalary() + "  ";
//        }

        final SharedPreferences pref;
        pref=getSharedPreferences("Data",MODE_PRIVATE);
        final SharedPreferences.Editor edit=pref.edit();

        logout=(Button)findViewById(R.id.logout);
//        DB=(Button)findViewById(R.id.database);
        show=(Button)findViewById(R.id.show);
        myProfile=(Button)findViewById(R.id.Myprfile);
        TrainTime=(Button)findViewById(R.id.traintimes);
        sp=(LinearLayout)findViewById(R.id.spinner);
        lapel=(LinearLayout)findViewById(R.id.Lapel);
        Fromspinner =(Spinner)findViewById(R.id.from);
        Tospinner =(Spinner)findViewById(R.id.to);
        li=(ListView)findViewById(R.id.listView);
        Times=(Button)findViewById(R.id.Times);
//        dell=(Button)findViewById(R.id.del);
        showdata=(TextView)findViewById(R.id.data);


                         //For Admin
        final int id=pref.getInt("ID", 0);
        if(id==4)show.setVisibility(View.VISIBLE);
        else show.setVisibility(View.INVISIBLE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.clear();
                edit.commit();
                Intent intent = new Intent(Searching.this, login.class);
                startActivity(intent);
                Toast.makeText(getApplication(), "hope see you soon  :'( ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        final SQLiteDatabase profiles=openOrCreateDatabase("prof", MODE_PRIVATE, null);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdata.setVisibility(View.VISIBLE);
                sp.setVisibility(View.INVISIBLE);
                li.setVisibility(View.INVISIBLE);

                String print="";
                final Cursor c=profiles.rawQuery("select * from profiles", null);

                while (c.moveToNext()) {
                    int passindex = c.getColumnIndexOrThrow("PASSWORD");
                    int nameindex = c.getColumnIndexOrThrow("NAME");
                    int idindex = c.getColumnIndexOrThrow("ID");
                    int moneyindex = c.getColumnIndexOrThrow("MONEY");

                    String passs = c.getString(passindex);
                    String namee = c.getString(nameindex);
                    int idd = c.getInt(idindex);
                    int moneyy = c.getInt(moneyindex);
                    //if(id==idd)
                    print+="ID : "+idd+" And it's name : "+namee+" have "+moneyy+"$"+"\n";
                }
                showdata.setText(print);

            }
        });
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdata.setVisibility(View.VISIBLE);
                sp.setVisibility(View.INVISIBLE);
                li.setVisibility(View.INVISIBLE);

                int id=pref.getInt("ID", 0);
                final Cursor c=profiles.rawQuery("select * from profiles", null);

                while (c.moveToNext()) {
                    int nameindex = c.getColumnIndexOrThrow("NAME");
                    int idindex = c.getColumnIndexOrThrow("ID");
                    int moneyindex = c.getColumnIndexOrThrow("MONEY");
                    int emailindex = c.getColumnIndexOrThrow("EMAIL");

                    String namee = c.getString(nameindex);
                    String emaill = c.getString(emailindex);
                    int idd = c.getInt(idindex);
                    int moneyy = c.getInt(moneyindex);
                    if(id==idd){
                    Intent p=new Intent(Searching.this,Profile.class);
                        p.putExtra("email",emaill);
                        p.putExtra("name",namee);
                        p.putExtra("money",moneyy);
                        p.putExtra("id",id);
                        startActivity(p);
                    }
                }

            }
        });
        TrainTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdata.setVisibility(View.INVISIBLE);
                lapel.setVisibility(View.INVISIBLE);
                sp.setVisibility(View.VISIBLE);
                Times.setVisibility(View.VISIBLE);

            }
        });
        Fromspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                From = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Tospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                To = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Times.setVisibility(View.INVISIBLE);
                lapel.setVisibility(View.VISIBLE);
                li.setVisibility(View.VISIBLE);
//String[] arr= getResources().getStringArray(R.array.ArCity);
                String filename = "";
                if (From.equals("طنطا")) {
                    if (To.equals("الاسكندرية")) {
                        filename = "TantaToAlex.txt";
                    } else if (To.equals("منوف")) {
                        filename = "TantaToMenof.txt";
                    } else if (To.equals("القاهرة")) {
                        filename = "TantaToCairo.txt";
                    }

                }

                String[] arr = readdata(filename);
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arr) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);
                        return view;
                    }
                };
                li.setAdapter(adapter);
            }
        });

        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          addandremovefav(parent,position); //My Method
               }
        });





//        DB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    profiles.execSQL("create table profiles(ID INTEGER PRIMARY KEY   AUTOINCREMENT,EMAIL TEXT NOT NULL,NAME TEXT NOT NULL,PASSWORD TEXT NOT NULL,MONEY INT NOT NULL);");
//                }catch (Exception e){
//                    profiles.execSQL("drop table profiles;");
//                    profiles.execSQL("create table profiles(ID INTEGER PRIMARY KEY   AUTOINCREMENT,EMAIL TEXT NOT NULL,NAME TEXT PRIMARY KEY,PASSWORD TEXT NOT NULL,MONEY INT NOT NULL);");
//
//                }
//                profiles.execSQL("insert into profiles (EMAIL,NAME,PASSWORD,MONEY) values(\'ahmed@yahoo.com\',\'ahmed\',\'000\',200);");
//                profiles.execSQL("insert into profiles (EMAIL,NAME,PASSWORD,MONEY) values(\'ali@yahoo.com\',\'ali\',\'333\',70);");
//                profiles.execSQL("insert into profiles (EMAIL,NAME,PASSWORD,MONEY) values(\'osama@yahoo.com\',\'osama\',\'222\',500);");
//                profiles.execSQL("insert into profiles (EMAIL,NAME,PASSWORD,MONEY) values(\'abdalnassef@gmail.com\',\'abdalnassef\',\'111\',1000);");
//                Toast.makeText(getApplication(),"Done Inserted",Toast.LENGTH_SHORT).show();
//            }
//        });

//        dell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                profiles.execSQL("drop table profiles;");
//            }
//        });


        /*Resources res=getResources();
        String[] names=res.getStringArray(R.array.namearray);
        int[] ides=res.getIntArray(R.array.IDarray);

        int id=getIntent().getIntExtra("myID", 0);
        String name="";

        for(int x=0;x<names.length;x++) {
            if(ides[x]==id){
                name=names[x];
            }
        }
        Toast.makeText(getApplicationContext(),("Hello : "+name),Toast.LENGTH_SHORT).show();
        */
    }


    public void writedata(String filename,String data) throws IOException {
        FileOutputStream fos = openFileOutput(filename, MODE_APPEND);
        OutputStreamWriter opw=new OutputStreamWriter(fos);
        opw.write(data);
        opw.close();
        fos.close();
    }//,String From,String To
    public String[] readdata(String filename)  {
        int recordsnumber=linecount(filename);
//        Toast.makeText(Searching.this, ""+recordsnumber, Toast.LENGTH_SHORT).show();
        String[] records = new String[recordsnumber];
        try {
            if (recordsnumber != 0) {
                FileInputStream fis = openFileInput(filename);
                InputStreamReader isr = new InputStreamReader(fis);

                char[] bufferreader = new char[1];
                String line = "";
                int inserted = 0;
                while (isr.read(bufferreader) > 0) {
                    if (bufferreader[0] != '\n') {
                        line += bufferreader[0];
                    } else {
                        String[] from = new String[recordsnumber];
                        String[] salary = new String[recordsnumber];
                        String[] to = new String[recordsnumber];
                        String[] kind = new String[recordsnumber];
                        String[] fromtime = new String[recordsnumber];
                        String[] totime = new String[recordsnumber];
                        String[] parts = line.split("\\|");
                        salary[inserted]=parts[0];
                        from[inserted] = parts[1];
                        to[inserted] = parts[2];
                        kind[inserted] = parts[3];
                        fromtime[inserted] = parts[4];
                        totime[inserted++] = parts[5];
                        records[inserted - 1] = salary[inserted - 1]+"  "+fromtime[inserted - 1] + "  " + totime[inserted - 1] + "  " + kind[inserted - 1];
                        //Toast.makeText(Searching.this, records[inserted-1], Toast.LENGTH_SHORT).show();
                        if (inserted == recordsnumber) break;
                        line = "";
                    }
                    bufferreader = new char[1];
                }
            } else {  Toast.makeText(Searching.this, "Empty File", Toast.LENGTH_SHORT).show();    }
        } catch (FileNotFoundException e) {
            try {
                FileOutputStream fos = openFileOutput(filename, MODE_APPEND);
                fos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            Toast.makeText(Searching.this, e.getMessage(), Toast.LENGTH_SHORT).show();}

//        Toast.makeText(Searching.this, e.getMessage(), Toast.LENGTH_SHORT).show();}
//    if(records[0].equals("-1")) Toast.makeText(Searching.this, "Empty", Toast.LENGTH_SHORT).show();
        return records;

    }
    public int linecount(String filename){
        int count=0;
        try{
            FileInputStream fis=openFileInput(filename);
            InputStreamReader isr=new InputStreamReader(fis);
            char[] bufferreader=new char[1];
            while(isr.read(bufferreader)>0){
                if(bufferreader[0]=='\n') {
                    count++;
                }
                bufferreader=new char[1];
            }
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            try {
                FileOutputStream fos = openFileOutput(filename, MODE_APPEND);
                fos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            Toast.makeText(Searching.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return count;
    }
    public void addandremovefav(AdapterView<?> parent, int position){
        final SQLiteDatabase profiles=openOrCreateDatabase("prof", MODE_PRIVATE, null);
        int id=getSharedPreferences("Data",MODE_PRIVATE).getInt("ID", 0);
        String num="0";
        if(parent.getItemAtPosition(position).toString().charAt(1)!=' ')
            num=""+parent.getItemAtPosition(position).toString().charAt(0)+parent.getItemAtPosition(position).toString().charAt(1);
        else num=""+parent.getItemAtPosition(position).toString().charAt(0);
        int number=Integer.parseInt(num);

        final Cursor c=profiles.rawQuery("select * from profiles where ID=" + id, null);
        int money=0;
        while (c.moveToNext()) {
            int moneyindex = c.getColumnIndexOrThrow("MONEY");
            money = c.getInt(moneyindex);
        }
        int sub = money - number;
        int sum = money + number;
//            profiles.execSQL("drop table Booked;");
//             profiles.execSQL("create table Booked(ID int ,bookdata TEXT ,PRIMARY KEY(ID,bookdata))");


        String dat=parent.getItemAtPosition(position) + "  " + From + "  " + To + "  ";
           try {
//               Toast.makeText(Searching.this, parent.getItemAtPosition(position) +"  "+ To + "  " + From, Toast.LENGTH_SHORT).show();


               profiles.execSQL("insert into Booked values(" + id + ",\'" +dat + "\')");
               profiles.execSQL("update profiles set MONEY=" + sub + " where ID=" + id);
               Toast.makeText(getApplicationContext(), "Done Inserted into Booked list", Toast.LENGTH_SHORT).show();


           }catch(Exception e){
               Toast.makeText(Searching.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            profiles.execSQL("delete from Booked where ID ="+id+" and bookdata = \'"+dat+"\';");//+id+"and bookdata = \'" + parent.getItemAtPosition(position) +"  "+From +"  "+To+"  "+ "\';");
               profiles.execSQL("update profiles set MONEY=" + sum + " where ID=" + id);
               //Toast.makeText(Searching.this,parent.getItemAtPosition(position).toString() , Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"Done removed from Booked list",Toast.LENGTH_SHORT).show();
        }

/*
           boolean found=false;boolean g=false;
        try {


            FileInputStream fis=openFileInput("Fav.txt");
            InputStreamReader isr=new InputStreamReader(fis);
            char[] inputbuffer=new char[1];
            int limit;
            String book="";
            while ((limit = isr.read(inputbuffer))>0){
                if(inputbuffer[0]!='\n')
                    book+=(String.copyValueOf(inputbuffer)) ;
                else{
                    if(book.equals(""+parent.getItemAtPosition(position))){
                        found=true; break;
                    }
                    book="";
                }
                inputbuffer=new char[1];
            }
            isr.close();
            fis.close();
            if(!found){
                FileOutputStream fos = openFileOutput("Fav.txt", MODE_APPEND);
                OutputStreamWriter opw = new OutputStreamWriter(fos);
                opw.write("" + parent.getItemAtPosition(position) + "\n");
                Toast.makeText(getApplicationContext(),"Done Inserted into favorites",Toast.LENGTH_SHORT).show();
                opw.flush();
                opw.close();
                fos.close();

            }else {


                FileInputStream infis = openFileInput("Fav.txt");
                InputStreamReader inisr = new InputStreamReader(infis);
                char[] ininputbuffer = new char[1];
                String inbook = "";
                String Infile = "";
                FileOutputStream fos;
                while ((inisr.read(ininputbuffer)) > 0) {
                    if (ininputbuffer[0] != '\n')
                        inbook += (String.copyValueOf(ininputbuffer));
                    else {
                        if (!inbook.equals("" + parent.getItemAtPosition(position))) {
                            Infile += inbook + "\n";
                        }
                        inbook = "";
                    }
                    ininputbuffer = new char[1];
                }
                fos = openFileOutput("Fav.txt", MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                osw.write(Infile);
                osw.flush();
                osw.close();
                fos.close();
                inisr.close();
                infis.close();
                Toast.makeText(Searching.this, "Done removed from favorites", Toast.LENGTH_SHORT).show();

        }
        } catch (FileNotFoundException e) {
            try {
                FileOutputStream fos = openFileOutput("Fav.txt", MODE_APPEND);
                fos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
                    Toast.makeText(getApplicationContext(),e.getMessage (),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }*/
    }
}

package com.example.paarth.assignment3;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    @SuppressWarnings("CanBeFinal")
    private static final String filename = "SharedValues";
    private SharedPreferences data;
    private String state;
    private static final int REQUEST_EXTERNAL_STORAGE_RESULT = 1;

    String FILENAME = "InternalFile.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);






        Button loginbutton = (Button) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener(){

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view){
                EditText login_uname = (EditText) findViewById(R.id.login_uname);
                EditText login_pass = (EditText) findViewById(R.id.login_pass);
                String uname = login_uname.getText().toString();
                String pass = login_pass.getText().toString();

                Log.d(TAG, "Clicked login button");





                //Shared Preferences
                data = getSharedPreferences(filename,0);
                String getuname = data.getString("username", "Couldn't get the value");
                String getpass = data.getString("password", "Couldn't get the value");

                checklogin(uname,getuname,pass,getpass);




                //Internal Storage
                BufferedWriter bw = null;
                try {
                    File file = new File(getApplicationContext().getFilesDir(), uname);

                    if (!file.exists()) {
                        //noinspection ResultOfMethodCallIgnored
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file);
                    bw = new BufferedWriter(fw);
                    bw.write("Username: " + uname+" Password: "+ pass);


                    System.out.println("File written Successfully");
                    System.out.println(getApplicationContext().getFilesDir());

                } catch (IOException e) {
                    e.printStackTrace();
                }finally
                {
                    try{
                        if(bw!=null)
                            bw.close();
                    }catch(Exception ex){
                        System.out.println("Error in closing the BufferedWriter"+ex);
                    }
                }


                //External Storage (Private)
                File folder = getExternalFilesDir("Assignment3");
                File myfile = new File(folder,uname);
                FileOutputStream fileOutputStream = null;
                String seconds = DateFormat.getDateTimeInstance().format(new Date());

                String message = "Username: " + uname + "\nPassword: " + pass + "\nLogin time: " + seconds;

                System.out.println(folder);
                try {

                    Toast.makeText(getApplicationContext(), "Message saved", Toast.LENGTH_SHORT).show();
                    fileOutputStream = new FileOutputStream(myfile);
                    fileOutputStream.write(message.getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    try{
                        if(fileOutputStream!=null){
                            fileOutputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                //External Storage (Public)
                File folder1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS);
                File myfile1 = new File(folder1, uname);
                FileOutputStream fileOutputStream1 = null;
                System.out.println(folder1);

                try {
                    fileOutputStream1 = new FileOutputStream(myfile1);
                    fileOutputStream1.write(message.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try{
                        if(fileOutputStream1!=null){
                            fileOutputStream1.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



                Intent sup = new Intent(view.getContext(), TableActivity.class);
                startActivity(sup);








            }

        });


    }




    private void checklogin(String uname, String getuname, String pass, String getpass){

        if(uname.equals(getuname) && pass.equals(getpass))
        {

            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(LoginActivity.this,"Please Try again", Toast.LENGTH_SHORT).show();

        }

    }











}

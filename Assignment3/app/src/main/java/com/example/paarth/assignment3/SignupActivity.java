package com.example.paarth.assignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private static final String filename = "SharedValues";
    private SharedPreferences data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signupbutton = (Button) findViewById(R.id.signupbutton);








        signupbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText signup_uname = (EditText) findViewById(R.id.signup_uname);
                EditText signup_pass = (EditText) findViewById(R.id.signup_pass);
                Log.d(TAG, "Clicked sign up button");
                String uname = signup_uname.getText().toString();
                String pass = signup_pass.getText().toString();

                data = getSharedPreferences(filename,0);
                SharedPreferences.Editor editor = data.edit();
                editor.putString("username", uname);
                editor.putString("password", pass);
                editor.commit();

                Intent sup = new Intent(view.getContext(), MainActivity.class);
                startActivity(sup);


            }



        });

    }


}

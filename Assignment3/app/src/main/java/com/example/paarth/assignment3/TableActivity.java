package com.example.paarth.assignment3;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TableActivity extends AppCompatActivity {
    private DatabaseActivity myDb;
    private EditText editUsername;
    private EditText editPassword;
    private EditText editID;
    private Button button_add;
    private Button button_retrieve;
    private Button button_update;
    private Button button_delete;
    private TextView showdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        myDb = new DatabaseActivity(this);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editID = (EditText) findViewById(R.id.editID);
        button_add = (Button) findViewById(R.id.button_add);
        button_retrieve = (Button) findViewById(R.id.button_retrieve);
        button_update = (Button) findViewById(R.id.button_update);
        button_delete = (Button) findViewById(R.id.button_delete);
        showdata = (TextView) findViewById(R.id.showdata);

        add_data();
        retrievedata();
        update_data();
        delete_data();
    }

    private void delete_data(){

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer isdeleted = myDb.deleteData(editID.getText().toString());

                if(isdeleted > 0){

                    Toast.makeText(TableActivity.this, "Values deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TableActivity.this, "Values not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void update_data(){

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isupdated = myDb.updateData(editID.getText().toString(), editUsername.getText().toString(), editPassword.getText().toString());

                if(isupdated){

                    Toast.makeText(TableActivity.this, "Values updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(TableActivity.this, "Values not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void add_data(){

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isinserted = myDb.insertData(editUsername.getText().toString(), editPassword.getText().toString());

                if(isinserted){
                    Toast.makeText(TableActivity.this, "Values inserted", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(TableActivity.this, "Values not inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void retrievedata(){

        button_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.retrieveData();

                if(res.getCount() == 0){

                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while(res.moveToNext()){

                    buffer.append("Id :").append(res.getString(0)).append("\n");
                    buffer.append("Username :").append(res.getString(1)).append("\n");
                    buffer.append("Password :").append(res.getString(2)).append("\n");

                }

                showdata.setText(buffer.toString());
            }
        });

    }



}

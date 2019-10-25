package com.anamaniac.companyassistant.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anamaniac.companyassistant.R;
import com.anamaniac.companyassistant.model.Usersmodel;
import com.anamaniac.companyassistant.presenter.BackgroundActivities;
import com.anamaniac.companyassistant.presenter.Backgroundservice;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Createuser extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);
        Button signup = (Button) findViewById(R.id.signup);

    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText firstname = (EditText) findViewById(R.id.firstname);
            EditText surname = (EditText) findViewById(R.id.surname);
            EditText email = (EditText) findViewById(R.id.emailAddress1);
            EditText password = (EditText) findViewById(R.id.password1);
            EditText phonenumber = (EditText) findViewById(R.id.phonenumber);
            String fname = firstname.getText().toString();
            String sname = surname.getText().toString();
            String Email = email.getText().toString();
            String phone = phonenumber.getText().toString();
            Usersmodel user = new Usersmodel(fname, sname, Email, phone);
            Intent intent = getIntent();
            String child = intent.getStringExtra("source");
            if (child=="employee")
            {Intent continuation = new Intent(Createuser.this,employees.class);
                startActivity(continuation);}
            else
                {Intent continuation = new Intent(Createuser.this,Dashboard.class);
                startActivity(continuation);}

            Intent addTaskIntent = new Intent(Createuser.this, Backgroundservice.class);
            addTaskIntent.setAction(BackgroundActivities.WriteToFirebase);
            addTaskIntent.putExtra("UserInformation",user);
            addTaskIntent.putExtra("source",child);
            startService(addTaskIntent);


        }
    });

    }

}

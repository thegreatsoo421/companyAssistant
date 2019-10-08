package com.anamaniac.companyassistant.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anamaniac.companyassistant.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Button employees = (Button) findViewById(R.id.employees);
        Button uploadButton = (Button) findViewById(R.id.upload);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mDbRef = mDatabase.getReference("Donor/Name");
        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent employee = new Intent(Dashboard.this,employees.class);
                startActivity(employee);
            }
        });
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,income.class);
                startActivity(intent);
            }
        });
        Button userManager = (Button) findViewById(R.id.Usermanager);
        userManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Profile.class);
                startActivity(intent);
            }
        });

    }
}

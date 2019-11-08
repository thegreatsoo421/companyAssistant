package com.anamaniac.companyassistant.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableRow;
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
//        Button employees = (Button) findViewById(R.id.employees);
//        Button uploadButton = (Button) findViewById(R.id.upload);
        RelativeLayout employees = (RelativeLayout) findViewById(R.id.EmployeeContainer);
        RelativeLayout Income = (RelativeLayout) findViewById(R.id.IncomeManagement);
        final RelativeLayout event = (RelativeLayout) findViewById(R.id.EventManager);
        TextView Today = (TextView) findViewById(R.id.Todaytask);
        TextView tomorrow = (TextView) findViewById(R.id.TomorrowsTask);
        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent employee = new Intent(Dashboard.this,employees.class);
                startActivity(employee);
            }
        });
//        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//        final DatabaseReference mDbRef = mDatabase.getReference("Donor/Name");
        Integer value = 000000;
        Integer value2 = 00000;
        String incomeinfo = "Your sales for today are : " + "\n" + "KES" + value.toString() + "  with an Average of Kes" + value2.toString() + "  per Store";
        String TodaysTask = "The task from Calender";
        String TomorrowTask = "The task from Calender";
        Today.setText("Todays Tasks is " + TodaysTask);
        tomorrow.setText("Tomorrow's tasks are " + TomorrowTask);
        Income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,income.class);
                startActivity(intent);
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Events.class);
                startActivity(intent);
            }
        });


    }
}

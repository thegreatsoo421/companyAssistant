package com.anamaniac.companyassistant.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anamaniac.companyassistant.R;

public class employees extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        final Button newEmployee = (Button) findViewById(R.id.button);
        newEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newemployee = new Intent(employees.this,Createuser.class);
                newemployee.putExtra("source","employee");
                startActivity(newemployee);

            }
        });
    }
}

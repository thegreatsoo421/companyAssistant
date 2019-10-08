package com.anamaniac.companyassistant.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.anamaniac.companyassistant.R;
import com.anamaniac.companyassistant.model.Incomemodel;
import com.anamaniac.companyassistant.presenter.BackgroundActivities;
import com.anamaniac.companyassistant.presenter.Backgroundservice;

public class income extends AppCompatActivity {


   private SharedPreferences list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Button button = (Button) findViewById(R.id.UpdateFirebase);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.IncomeInput);
                EditText Loss = (EditText) findViewById(R.id.Losses);
                int Gain = Integer.parseInt(input.getText().toString());
                int Losses =  -1 * Integer.parseInt(Loss.getText().toString());
                Incomemodel incomemodel = new Incomemodel(Gain, Losses);
                Intent intent = new Intent(income.this, Backgroundservice.class);
                intent.setAction(BackgroundActivities.AddIncomeToFirebaseText);
                intent.putExtra("Values",incomemodel);
                startService(intent);

            }
        });
        Button next = findViewById(R.id.ToIncomeView);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(income.this, IncomeView.class);
                startActivity(intent);
            }
        });
        list = getSharedPreferences("Pref name",0);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceEditor = list.edit();
        EditText input =(EditText) findViewById(R.id.IncomeInput);
        EditText Loss = (EditText) findViewById(R.id.Losses);
        preferenceEditor.putString("gain",input.getText().toString());
        preferenceEditor.putString("Loss",Loss.getText().toString());
        preferenceEditor.commit();
    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences.Editor preferenceEditor = list.edit();
        preferenceEditor.apply();
        EditText input =(EditText) findViewById(R.id.IncomeInput);
        EditText Loss = (EditText) findViewById(R.id.Losses);
        String Gain = list.getString("gain","");
        String Losses = list.getString("Loss","");
        input.setText(Gain);
        Loss.setText(Losses);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor preferenceEditor = list.edit();
        preferenceEditor.clear();
        preferenceEditor.commit();
        Log.d("on destroy","On destroy Activity");
    }
}

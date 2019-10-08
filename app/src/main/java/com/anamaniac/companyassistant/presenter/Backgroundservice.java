package com.anamaniac.companyassistant.presenter;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


import com.anamaniac.companyassistant.model.Incomemodel;
import com.anamaniac.companyassistant.model.Usersmodel;

public class Backgroundservice extends IntentService {

    public Backgroundservice() {
        super("Backgroundservice");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("OnHandleIntent","The method has been called");

        BackgroundActivities backgroundactivities = new BackgroundActivities(this);
        String Action = intent.getAction();
        String child = intent.getStringExtra("source");
        if (BackgroundActivities.WriteToFirebase.equals(Action)) {

            if (child.equals("employee")) {
                Usersmodel user = intent.getParcelableExtra("UserInformation");
                backgroundactivities.EmployeeToFirebase(user);
            } else {
                Usersmodel user = intent.getParcelableExtra("UserInformation");
                backgroundactivities.WriteUserToFirebase(user);
            }
        }
            else
                {if (BackgroundActivities.AddIncomeToFirebaseText.equals(Action)) {
                Incomemodel incomemodel = intent.getParcelableExtra("Values");
                backgroundactivities.AddIncomeToFirebase(incomemodel);
            }}
        }


    }
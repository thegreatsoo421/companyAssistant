package com.anamaniac.companyassistant.presenter;


import android.content.Context;
import android.util.Log;

import com.anamaniac.companyassistant.model.Incomemodel;
import com.anamaniac.companyassistant.model.Usersmodel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class BackgroundActivities {
public static final String WriteToFirebase = "create user";
public static final String AddIncomeToFirebaseText = "Add income";
    public Context mContext;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    public String source2;


    public BackgroundActivities(Context context){
        this.mContext = context;
    }

    public void GetChild(String child) {
        final String source = child;
        source2 = source;
    }

    public void WriteUserToFirebase(final Usersmodel user)
    {

        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference().child("User");
        mDbRef.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("success","user created");
            }
        });


    }


    public void EmployeeToFirebase(final  Usersmodel user)
    {
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference().child("employee");
        mDbRef.push().setValue(user);
    }
    public void AddIncomeToFirebase(final Incomemodel incomemodel){
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference().child("Income");
        mDbRef.push().setValue(incomemodel);
    }


}

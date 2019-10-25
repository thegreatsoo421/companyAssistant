package com.anamaniac.companyassistant.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anamaniac.companyassistant.presenter.FirebaseLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileViewModel extends ViewModel {
    private static final DatabaseReference employee = FirebaseDatabase.getInstance().getReference();
    private final FirebaseLiveData liveData = new FirebaseLiveData(employee);

    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData()
    {
        return liveData;
    }
    public FirebaseLiveData getLiveData()
    {
        return liveData;
    }
}

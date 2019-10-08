package com.anamaniac.companyassistant.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.anamaniac.companyassistant.presenter.FirebaseLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IncomeViewModel extends ViewModel {
    private static final DatabaseReference Income =
            FirebaseDatabase.getInstance().getReference();
    private final FirebaseLiveData liveData = new FirebaseLiveData(Income);

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

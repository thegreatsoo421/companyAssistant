package com.anamaniac.companyassistant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.anamaniac.companyassistant.R;
import com.anamaniac.companyassistant.model.IncomeViewModel;
import com.anamaniac.companyassistant.model.Incomemodel;
import com.anamaniac.companyassistant.presenter.IncomeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class IncomeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_view);
        final List<Incomemodel> incomelist7 = new ArrayList();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final IncomeAdapter incomeAdapter = new IncomeAdapter(this);
        recyclerView.setAdapter(incomeAdapter);

        IncomeViewModel viewModel= ViewModelProviders.of(this).get(IncomeViewModel.class);
        LiveData<DataSnapshot> livedata = viewModel.getDataSnapshotLiveData();
        livedata.observe(this, new Observer<DataSnapshot>()
        {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot)
            {
                DataSnapshot eventsDetails = dataSnapshot.child("Income");
                Boolean exist = eventsDetails.exists();
                Log.d("Confirming","This confirms that the datasnapshot exists " + exist);
                Iterable<DataSnapshot> eventsDatasnapshot = eventsDetails.getChildren();
                for(DataSnapshot incomelist : eventsDatasnapshot)
                {
                    new Incomemodel();
                    Incomemodel incomemodel = incomelist.getValue(Incomemodel.class);
                    incomelist7.add(incomemodel);
                }
                incomeAdapter.setIncomelist(incomelist7);

            }
        });
    }
}

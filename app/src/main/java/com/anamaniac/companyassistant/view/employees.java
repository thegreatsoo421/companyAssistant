package com.anamaniac.companyassistant.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.anamaniac.companyassistant.R;
import com.anamaniac.companyassistant.model.IncomeViewModel;
import com.anamaniac.companyassistant.model.Incomemodel;
import com.anamaniac.companyassistant.model.ProfileViewModel;
import com.anamaniac.companyassistant.model.Usersmodel;
import com.anamaniac.companyassistant.presenter.EmployeesInterface;
import com.anamaniac.companyassistant.presenter.IncomeAdapter;
import com.anamaniac.companyassistant.presenter.ProfileViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class employees extends AppCompatActivity implements EmployeesInterface {
    final List<Usersmodel> employeeslist = new ArrayList<>();
    private ProfileViewAdapter profileViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        final Button newEmployee = (Button) findViewById(R.id.button);

        RecyclerView recyclerView = findViewById(R.id.EmployeeView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final ProfileViewAdapter profileViewAdapter = new ProfileViewAdapter(this);
        recyclerView.setAdapter(profileViewAdapter);

        ProfileViewModel viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        LiveData<DataSnapshot> livedata = viewModel.getDataSnapshotLiveData();
        livedata.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                DataSnapshot eventsDetails = dataSnapshot.child("employee");
                Boolean exist = eventsDetails.exists();
                Log.d("Confirming", "This confirms that the datasnapshot exists " + exist);
                Iterable<DataSnapshot> eventsDatasnapshot = eventsDetails.getChildren();
                for (DataSnapshot employees : eventsDatasnapshot) {
                    new Usersmodel();
                    Usersmodel employee = employees.getValue(Usersmodel.class);
                    employeeslist.add(employee);
                }
                profileViewAdapter.setUserlist(employeeslist);

            }
        });


        newEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newemployee = new Intent(employees.this, Createuser.class);
                newemployee.putExtra("source", "employee");
                startActivity(newemployee);

            }
        });
        profileViewAdapter.setClicklistener(this);
    }


    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(employees.this,Profile.class);
        intent.putExtra("profile",employeeslist.get(position));
        startActivity(intent);
    }

}

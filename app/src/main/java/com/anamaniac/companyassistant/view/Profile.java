package com.anamaniac.companyassistant.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anamaniac.companyassistant.R;
import com.anamaniac.companyassistant.model.IncomeViewModel;
import com.anamaniac.companyassistant.model.Incomemodel;
import com.anamaniac.companyassistant.model.ProfileViewModel;
import com.anamaniac.companyassistant.model.Usersmodel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GlideUrl;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.annotations.Nullable;

public class Profile extends AppCompatActivity {
    Integer MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private SharedPreferences image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView imageView = (ImageView) findViewById(R.id.profile_image);

      /*  IncomeViewModel viewModel = ViewModelProviders.of(this).get(IncomeViewModel.class);
        LiveData<DataSnapshot> livedata = viewModel.getDataSnapshotLiveData();
        livedata.observe(this, new Observer<DataSnapshot>()
        {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot)
            {
                DataSnapshot eventsDetails = dataSnapshot.child("employee");
                Boolean exist = eventsDetails.exists();
                Log.d("Confirming","This confirms that the datasnapshot exists " + exist);
                Iterable<DataSnapshot> eventsDatasnapshot = eventsDetails.getChildren();
                for(DataSnapshot userModel : eventsDatasnapshot)
                {
                    new Usersmodel();
                    Usersmodel user = userModel.getValue(Usersmodel.class);
                    EditText Name = findViewById(R.id.ProfileName);
                    EditText surname = findViewById(R.id.ProfileSurName);
                    EditText email = findViewById(R.id.ProfileEmail);
                    EditText PhoneNumber = findViewById(R.id.ProfilePhoneNumber);
                    Name.setText(user.getFirstname());
                    surname.setText(user.getSurname());
                    email.setText(user.getEmail());
                    PhoneNumber.setText(user.getPhonenumber());
                }

            }
        });*/
        EditText Name = findViewById(R.id.ProfileName);
        EditText surname = findViewById(R.id.ProfileSurName);
        EditText email = findViewById(R.id.ProfileEmail);
        EditText PhoneNumber = findViewById(R.id.ProfilePhoneNumber);
        Intent intent = getIntent();
        Usersmodel profile = intent.getParcelableExtra("profile");
        assert profile != null;
        Name.setText(profile.getFirstname());
        surname.setText(profile.getSurname());
        email.setText(profile.getEmail());
        PhoneNumber.setText(profile.getPhonenumber());
        String url = profile.getUrl();
        Glide.with(this).load(url).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRead();
            }
        });
        image = getSharedPreferences("pref name",0);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (requestCode == 1) {

                if (resultCode == RESULT_OK) {
                    final Uri imageUri = data.getData();
                    String url = "https://images.unsplash.com/photo-1571747601169-ffb297b32117?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80";
                    ImageView imageView = (ImageView) findViewById(R.id.profile_image);
                    TextView textView = (TextView) findViewById(R.id.UriView);
                    textView.setText(imageUri.toString());
                    SharedPreferences.Editor preferenceEditor = image.edit();
                    preferenceEditor.clear();

                    preferenceEditor.putString("Uri",url);
                    preferenceEditor.commit();

                   imageView.setImageURI(imageUri);
                }
            }
        }catch (Exception e)
        {

        }
    }
    public void startingUpTheCameraPicker()
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }
    public void requestRead()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
//             In this code the user is requesting for permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
        else
        {
//                In this case the permissions have been offered already
            startingUpTheCameraPicker();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
//                In this case the permission has been granted and the user can start the camera picker
                startingUpTheCameraPicker();
            } else {
                // Permission Denied
                Toast.makeText(Profile.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences.Editor preferenceEditor = image.edit();
        String UriString  = image.getString("Uri","https://images.unsplash.com/photo-1571747601169-ffb297b32117?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80");
        Uri imageUri = Uri.parse(UriString);
        ImageView imageView = (ImageView) findViewById(R.id.profile_image);
        imageView.setImageURI(imageUri);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor preferenceeditor = image.edit();
        preferenceeditor.clear();
        preferenceeditor.clear();

    }
}


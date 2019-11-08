package com.anamaniac.companyassistant.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anamaniac.companyassistant.R;
import com.anamaniac.companyassistant.model.Usersmodel;
import com.anamaniac.companyassistant.presenter.BackgroundActivities;
import com.anamaniac.companyassistant.presenter.Backgroundservice;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Createuser extends AppCompatActivity {
    Integer MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final String TAG = "MainActivity";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    public Uri ImageUri;
    public String uriString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);
        Button signup = (Button) findViewById(R.id.signup);
        ImageView selectimage = (ImageView) findViewById(R.id.selectImage);
        Button runButton = (Button) findViewById(R.id.selectImageButton);

    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText firstname = (EditText) findViewById(R.id.firstname);
            EditText surname = (EditText) findViewById(R.id.surname);
            EditText email = (EditText) findViewById(R.id.emailAddress1);
            EditText password = (EditText) findViewById(R.id.password1);
            EditText phonenumber = (EditText) findViewById(R.id.phonenumber);

            String fname = firstname.getText().toString();
            String sname = surname.getText().toString();
            String Email = email.getText().toString();
            String phone = phonenumber.getText().toString();
            Usersmodel user = new Usersmodel(fname, sname, Email, phone,uriString);

            Log.d("UserUpload","This is the user to upload " + user);
            Intent intent = getIntent();
            String child = intent.getStringExtra("source");
            if (child=="employee")
            {Intent continuation = new Intent(Createuser.this,employees.class);
                startActivity(continuation);}
            else
                {Intent continuation = new Intent(Createuser.this,Dashboard.class);
                startActivity(continuation);}

            Intent addTaskIntent = new Intent(Createuser.this, Backgroundservice.class);
            addTaskIntent.setAction(BackgroundActivities.WriteToFirebase);
            addTaskIntent.putExtra("UserInformation",user);
            addTaskIntent.putExtra("source",child);
            startService(addTaskIntent);


        }
    });
        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRead();
            }
        });
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profile = new Profile();
                profile.requestRead();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityresult", "onActivityResult: is run");

        try {

            if (requestCode == 1) {

                if (resultCode == RESULT_OK) {
                    final Uri imageUri = data.getData();
                    ImageView imageView = (ImageView) findViewById(R.id.selectImage);
                    imageView.setImageURI(imageUri);
                    ImageUri = imageUri;


                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference storageRef = storage.getReference().child("Profile pictures");
                    final StorageReference imagesRef = storageRef.child(imageUri.getLastPathSegment());
                    UploadTask uploadTask = imagesRef.putFile(imageUri);
                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }

                            // Continue with the task to get the download URL
                            return imagesRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful())
                            {
                                Uri downloadUri = task.getResult();
                                String image = downloadUri.toString();
                                uriString = downloadUri.toString();
                                Log.d("SelectedImage","This is the selected image " + image);

                            } else
                            {
                                // Handle failures
                                // ...

                            }
                        }
                    });

                }}}
        catch (Exception e)
        {
            Log.d("Error","There is an error " + e.getMessage());
        }

    }
    public void startingUpTheCameraPicker()
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
        Log.d("camera picker", "startingUpTheCameraPicker: is run");
    }
    public void requestRead()
    {
        Log.d("request read", "requestRead: has run");
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
                Toast.makeText(Createuser.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}

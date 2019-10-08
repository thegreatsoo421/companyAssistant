package com.anamaniac.companyassistant.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anamaniac.companyassistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivty extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        Button createuser = (Button) findViewById(R.id.ToSignUp);
        createuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivty.this,Createuser.class);
                intent.putExtra("source","User");
                startActivity(intent);
            }
        });
        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EditText emailaddress = (EditText) findViewById(R.id.emailAddress);
               EditText passwordtext = (EditText) findViewById(R.id.password);
               Intent intent = new Intent(LoginActivty.this,Dashboard.class);
               startActivity(intent);

               /* mAuth = FirebaseAuth.getInstance();
                String email = emailaddress.getText().toString();
                String password = passwordtext.getText().toString();
                if (email.equals("") || password.equals("")) {Toast.makeText(LoginActivty.this,"insert valid email",Toast.LENGTH_SHORT);} else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivty.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("email address", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("email fail", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivty.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }}
        */}});


    }

    private void updateUI(FirebaseUser user)
    {

    }
    }


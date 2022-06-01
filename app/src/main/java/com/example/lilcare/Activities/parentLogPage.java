package com.example.lilcare.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.lilcare.Fragments.ProfileFragment;
import com.example.lilcare.databinding.ActivityParentLogBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.regex.Pattern;

public class parentLogPage extends AppCompatActivity {
    ActivityParentLogBinding parentLogBinding;

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentLogBinding = ActivityParentLogBinding.inflate(getLayoutInflater());
        setContentView(parentLogBinding.getRoot());
        
        mAuth = FirebaseAuth.getInstance();

        parentLogBinding.parentLogBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePassword() | validateEmail()){
                    parentLogBinding.bar.setVisibility(View.VISIBLE);

                    String email = parentLogBinding.parentLogIDTB.getEditText().getText().toString();
                    String password = parentLogBinding.parentLogPassTB.getEditText().getText().toString();

                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(parentLogPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        parentLogBinding.bar.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(parentLogPage.this, HomePage.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("email",mAuth.getCurrentUser().getEmail());
                                        ProfileFragment profile = new ProfileFragment();
                                        profile.setArguments(bundle);
                                        Toast.makeText(parentLogPage.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                    else {
                                        parentLogBinding.bar.setVisibility(View.INVISIBLE);
                                        parentLogBinding.parentLogID.setText("");
                                        parentLogBinding.parentLogPass.setText("");
                                        Toast.makeText(parentLogPage.this, "Invalid User details!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });



        parentLogBinding.regforparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(parentLogPage.this, parentRegPage.class);
                startActivity(i);
                //hii
            }
        });


        parentLogBinding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(parentLogPage.this, HomePage.class);
                startActivity(i);
            }
        });
    }

    private boolean validateEmail() {

        String emailInput = parentLogBinding.parentLogIDTB.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            parentLogBinding.parentLogIDTB.setError("Field can not be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            parentLogBinding.parentLogIDTB.setError("Please enter a valid email address");
            return false;
        } else {
            parentLogBinding.parentLogIDTB.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = parentLogBinding.parentLogPassTB.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            parentLogBinding.parentLogPassTB.setError("Field can not be empty");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            parentLogBinding.parentLogPassTB.setError("Password is too weak");
            return false;
        } else {
            parentLogBinding.parentLogPassTB.setError(null);
            return true;
        }
    }


}
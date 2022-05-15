package com.example.lilcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class parentLogPage extends AppCompatActivity implements View.OnClickListener {
    Button regforparent, home, signIn;
    private EditText editTextEmail,editTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_log);

        regforparent = (Button) findViewById(R.id.regforparent);
        regforparent.setOnClickListener(this);
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(this);

        editTextEmail = (TextInputEditText) findViewById(R.id.parent_mail);
        editTextEmail.setOnClickListener(this);
        editTextPassword = (TextInputEditText) findViewById(R.id.parent_pass);
        editTextPassword.setOnClickListener(this);
        signIn = (Button) findViewById(R.id.parent_login);
        signIn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.regforparent:
                startActivity(new Intent(parentLogPage.this,parentRegPage.class));
                break;

            case R.id.home:
                startActivity(new Intent(parentLogPage.this,AboutUs.class));
                break;

            case R.id.parent_login:
                userLogin();


        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required!!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Provide a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPassword.setError("Password should be atleast 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(parentLogPage.this,HomePage.class));
                } else {
                    Toast.makeText(parentLogPage.this,"Failed to login,\n Please re-check your credentials & Try Again!!",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
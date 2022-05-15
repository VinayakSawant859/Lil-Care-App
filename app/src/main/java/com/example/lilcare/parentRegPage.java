package com.example.lilcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lilcare.databinding.ActivityParentRegBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

public class parentRegPage extends AppCompatActivity {

    private TextView textView;
    private TextInputEditText childProfile,childName,childAge,parentName,parentEmail,newPass,confirmPass,childAddress,Phone1,Phone2,medicState;
    private Button nextbtn;
    private FirebaseAuth mAuth;

    ActivityParentRegBinding parentRegBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAuth = FirebaseAuth.getInstance();
        parentRegBinding = ActivityParentRegBinding.inflate(getLayoutInflater());
        setContentView(parentRegBinding.getRoot());


        DatabaseClass dao = new DatabaseClass();

    }

    public void nextbtn(View view) {

        childName = (TextInputEditText) findViewById(R.id.childName);
        childAge = (TextInputEditText) findViewById(R.id.childAge);
        parentName = (TextInputEditText) findViewById(R.id.parentName);
        parentEmail = (TextInputEditText) findViewById(R.id.parentEmail);
        newPass = (TextInputEditText) findViewById(R.id.newPass);
        confirmPass = (TextInputEditText) findViewById(R.id.confirmPass);
        Phone1 = (TextInputEditText) findViewById(R.id.Phone1);
        Phone2 = (TextInputEditText) findViewById(R.id.Phone2);
        childAddress = (TextInputEditText) findViewById(R.id.childAddress);
        medicState = (TextInputEditText) findViewById(R.id.medicState);

        String cname = childName.getText().toString().trim();
        String cage = childAge.getText().toString().trim();
        String pname = parentName.getText().toString().trim();
        String email= parentEmail.getText().toString().trim();
        String pass = newPass.getText().toString().trim();
        String confirmpass = confirmPass.getText().toString().trim();
        String ph1 = Phone1.getText().toString().trim();
        String ph2 = Phone2.getText().toString().trim();
        String address = childAddress.getText().toString().trim();
        String medical = medicState.getText().toString().trim();

        if(cname.isEmpty()){
            childName.setError("Child Name is required");
            childName.requestFocus();
            return;
        }

        if(cage.isEmpty()){
            childAge.setError("Child Age is required");
            childAge.requestFocus();
            return;
        }

        if(pname.isEmpty()){
            parentName.setError("Parent Name is required");
            parentName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            parentEmail.setError("Email is required!");
            parentEmail.requestFocus();
            return;

        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            parentEmail.setError("Please provide a valid email!");
            parentEmail.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            newPass.setError("Password is Required!");
            newPass.requestFocus();
            return;
        }

        if(pass.length() < 6){
            newPass.setError("Min length of the password should be 6 characters!");
            newPass.requestFocus();
            return;
        }

        if(confirmpass.isEmpty()){
            confirmPass.setError("This cannot be empty");
            confirmPass.requestFocus();
            return;
        }
        if(!confirmpass.matches(pass)){
            confirmPass.setError("Password does not match");
            confirmPass.requestFocus();
            return;
        }

        if(address.isEmpty()){
            childAddress.setError("Address is required");
            childAddress.requestFocus();
            return;
        }

        if(ph1.isEmpty()){
            Phone1.setError("Please enter your number");
            Phone1.requestFocus();
            return;
        }

        if(ph1.length() < 10){
            Phone1.setError("Enter your complete number");
            Phone1.requestFocus();
            return;
        }

        if(ph2.isEmpty()){
            Phone2.setError("Please enter your number");
            Phone2.requestFocus();
            return;
        }
        if(ph2.length() < 10){
            Phone2.setError("Enter your complete number");
            Phone2.requestFocus();
            return;
        }

        if(ph1.matches(ph2)){
            Phone2.setError("Provide a different number");
            Phone2.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Users user = new Users(childName,childAge,childAddress,parentName,parentEmail,confirmPass,Phone1,Phone2);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Toast.makeText(parentRegPage.this,"User registered Successfully",Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(parentRegPage.this,parentLogPage.class);
                                                startActivity(i);
                                            }else{
                                                Toast.makeText(parentRegPage.this,"Failed to register! Try Again!!",Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });


                        }else{
                            Toast.makeText(parentRegPage.this,"Failed to register! Try Again!!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
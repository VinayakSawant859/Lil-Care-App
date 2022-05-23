package com.example.lilcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lilcare.databinding.ActivityParentLogBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class parentLogPage extends AppCompatActivity {
    ActivityParentLogBinding parentLogBinding;
    private FirebaseAuth auth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://userauthenticationapp-4f6d3-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentLogBinding = ActivityParentLogBinding.inflate(getLayoutInflater());
        setContentView(parentLogBinding.getRoot());

        auth = FirebaseAuth.getInstance();

        parentLogBinding.parentLogBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt_email = parentLogBinding.parentLogID.getText().toString();
                String txt_pass = parentLogBinding.parentLogPass.getText().toString();

                if (txt_email.isEmpty() || txt_pass.isEmpty()){
                   Toast.makeText(parentLogPage.this, "Please enter credentials!", Toast.LENGTH_SHORT).show();
                }
                else {

                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(txt_email)){
                                final  String getPass = snapshot.child(txt_pass).child("newPassword").getValue(String.class);
                                
                                if (getPass.equals(txt_pass)){
                                    Toast.makeText(parentLogPage.this, "abc", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(parentLogPage.this, HomePage.class));
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

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

//    private void loginUser(String email, String pass) {
//        auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//                Toast.makeText(parentLogPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(parentLogPage.this, HomePage.class));
//                finish();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(parentLogPage.this, "Login Failed!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
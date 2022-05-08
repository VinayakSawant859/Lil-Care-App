package com.example.lilcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lilcare.databinding.ActivityParentRegBinding;

public class parentRegPage extends AppCompatActivity {
    ActivityParentRegBinding parentRegBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentRegBinding = ActivityParentRegBinding.inflate(getLayoutInflater());
        setContentView(parentRegBinding.getRoot());

        DatabaseClass dao = new DatabaseClass();

        parentRegBinding.nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users user = new Users(parentRegBinding.childProfile.getText().toString(),
                        parentRegBinding.childName.getText().toString(),
                        parentRegBinding.childAge.getText().toString(),
                        parentRegBinding.parentName.getText().toString(),
                        parentRegBinding.parentEmail.getText().toString(),
                        parentRegBinding.newPass.getText().toString(),
                        parentRegBinding.confirmPass.getText().toString(),
                        parentRegBinding.Phone1.getText().toString(),
                        parentRegBinding.Phone2.getText().toString(),
                        parentRegBinding.childAddress.getText().toString(),
                        parentRegBinding.medicState.getText().toString());

                if (parentRegBinding.newPass.getText().toString().equals(parentRegBinding.confirmPass.getText().toString())) {
                    if (!(parentRegBinding.childName.getText().toString() == null)){
                        dao.add(user).addOnSuccessListener(suc -> {
                            Toast.makeText(parentRegPage.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(er -> {
                            Toast.makeText(parentRegPage.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        Intent i = new Intent(parentRegPage.this, HomePage.class);
                        startActivity(i);
                    }
                }
                else{
                    Toast.makeText(parentRegPage.this, "Confirm password doesn't match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
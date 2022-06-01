package com.example.lilcare.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.lilcare.Adapters.DatabaseClass;
import com.example.lilcare.Models.Users;
import com.example.lilcare.databinding.ActivityParentRegBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;
import java.util.regex.Pattern;

public class parentRegPage extends AppCompatActivity {
    ActivityParentRegBinding parentRegBinding;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private Uri selectedImage;


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentRegBinding = ActivityParentRegBinding.inflate(getLayoutInflater());
        setContentView(parentRegBinding.getRoot());

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseStorage = FirebaseStorage.getInstance();
        DatabaseClass dao = new DatabaseClass();

        parentRegBinding.childImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i, 45);
            }
        });

        parentRegBinding.nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isInputEmpty = validateTextFields();
                if (!isInputEmpty){
                    if (validateEmail() | validatePassword()){
                        if (parentRegBinding.newPass.getText().toString().equals(parentRegBinding.confirmPass.getText().toString())){
                            if (selectedImage != null){
                                profileUpload();
                            }else {
                                Toast.makeText(dao, "Select a Profile image!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            parentRegBinding.confirmPassTB.setError("Confirm Password not matching!");
                        }
                    }
                }

            }
        });
    }

    private boolean validateEmail() {

        String emailInput = parentRegBinding.parentEmailTB.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            parentRegBinding.parentEmailTB.setError("Field can not be empty");
            parentRegBinding.parentEmailTB.requestFocus();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            parentRegBinding.parentEmailTB.setError("Please enter a valid email address");
            parentRegBinding.parentEmailTB.requestFocus();
            return false;
        } else {
            parentRegBinding.parentEmailTB.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = parentRegBinding.confirmPassTB.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            parentRegBinding.confirmPassTB.setError("Field can not be empty");
            parentRegBinding.confirmPassTB.requestFocus();
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            parentRegBinding.confirmPassTB.setError("Password is too weak");
            parentRegBinding.confirmPassTB.requestFocus();
            return false;
        } else {
            parentRegBinding.confirmPassTB.setError(null);
            return true;
        }
    }

    private Boolean validateTextFields(){
        Boolean isEmptyFields = false;

        String cName = parentRegBinding.childNameTB.getEditText().getText().toString().trim();
        String cAge = parentRegBinding.childAgeTB.getEditText().getText().toString().trim();
        String cpName = parentRegBinding.parentNameTB.getEditText().getText().toString().trim();
        String cpEmail = parentRegBinding.parentEmailTB.getEditText().getText().toString().trim();
        String cpPass = parentRegBinding.newPassTB.getEditText().getText().toString().trim();
        String cpConfPass = parentRegBinding.confirmPassTB.getEditText().getText().toString().trim();
        String cpAddress = parentRegBinding.childAddressTB.getEditText().getText().toString().trim();
        String cpPhone1 = parentRegBinding.Phone1TB.getEditText().getText().toString().trim();
        String cpPhone2 = parentRegBinding.Phone2TB.getEditText().getText().toString().trim();
        String cMedic = parentRegBinding.medicStateTB.getEditText().getText().toString().trim();


        if (cName == null || cName.equalsIgnoreCase("")){
            parentRegBinding.childNameTB.setError("Fields can't be empty");
            parentRegBinding.childNameTB.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.childNameTB.setError(null);
            isEmptyFields = false;
        }

        if (cAge == null || cAge.equalsIgnoreCase("")){
            parentRegBinding.childAgeTB.setError("Fields can't be empty");
            parentRegBinding.childAgeTB.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.childAgeTB.setError(null);
            isEmptyFields = false;
        }

        if (cpName == null || cpName.equalsIgnoreCase("")){
            parentRegBinding.parentNameTB.setError("Fields can't be empty");
            parentRegBinding.parentNameTB.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.parentNameTB.setError(null);
            isEmptyFields = false;
        }

        if (cpEmail == null || cpEmail.equalsIgnoreCase("")){
            parentRegBinding.parentEmailTB.setError("Fields can't be empty");
            parentRegBinding.parentEmailTB.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.parentEmailTB.setError(null);
            isEmptyFields = false;
        }

        if (cpPass == null || cpPass.equalsIgnoreCase("")){
            parentRegBinding.newPass.setError("Fields can't be empty");
            parentRegBinding.newPass.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.newPass.setError(null);
            isEmptyFields = false;
        }

        if (cpConfPass == null || cpConfPass.equalsIgnoreCase("")){
            parentRegBinding.confirmPassTB.setError("Fields can't be empty");
            parentRegBinding.confirmPassTB.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.confirmPassTB.setError(null);
            isEmptyFields = false;
        }

        if (cpAddress == null || cpAddress.equalsIgnoreCase("")){
            parentRegBinding.childAddressTB.setError("Fields can't be empty");
            parentRegBinding.childAddressTB.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.childAddressTB.setError(null);
            isEmptyFields = false;
        }

        if (cpPhone1 == null || cpPhone1.equalsIgnoreCase("")){
            parentRegBinding.Phone1TB.setError("Fields can't be empty");
            parentRegBinding.Phone1TB.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.Phone1TB.setError(null);
            isEmptyFields = false;
        }

        if (cpPhone2 == null || cpPhone2.equalsIgnoreCase("")){
            parentRegBinding.Phone2TB.setError("Fields can't be empty");
            parentRegBinding.Phone2TB.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.Phone2TB.setError(null);
            isEmptyFields = false;
        }

        if (cMedic == null || cMedic.equalsIgnoreCase("")){
            parentRegBinding.medicStateTB.setError("Fields can't be empty");
            parentRegBinding.medicStateTB.requestFocus();
            isEmptyFields = true;
        }
        else {
            parentRegBinding.medicStateTB.setError(null);
            isEmptyFields = false;
        }

        return isEmptyFields;
    }

    public String getExtension(){
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(getContentResolver().getType(selectedImage));
    }

    public void profileUpload(){
        if (selectedImage != null){
            final StorageReference reference = FirebaseStorage.getInstance().getReference("myProfiles/"+System.currentTimeMillis()+"."+getExtension());
            reference.putFile(selectedImage)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String imageURL = uri.toString();

                                        Users user = new Users(imageURL,
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

                                        String email = parentRegBinding.parentEmail.getText().toString();
                                        String password = parentRegBinding.newPass.getText().toString();

                                        mAuth = FirebaseAuth.getInstance();

                                        mAuth.createUserWithEmailAndPassword(email,password)
                                                .addOnCompleteListener(parentRegPage.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(parentRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Toast.makeText(parentRegPage.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                        databaseReference.child(Objects.requireNonNull(databaseReference.push().getKey())).setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(parentRegPage.this, "Succesfully Uploaded!", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                                                        finishAffinity();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(parentRegPage.this, "Uploading Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
        }else {
            Toast.makeText(parentRegPage.this, "Select a Profile image!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            if (data.getData() != null){
                parentRegBinding.childImage.setImageURI(data.getData());
                selectedImage = data.getData();
            }
        }
    }
}
package com.example.lilcare.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.lilcare.databinding.FragmentProfilePageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    public static final String TAG = "1";
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    public static final String USERS = "Users";
    private FragmentProfilePageBinding binding;
    private FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        binding = FragmentProfilePageBinding.inflate(inflater,container,false);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference(USERS);

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    if (ds.child("parentEmail").getValue().equals(mAuth.getCurrentUser().getEmail())){
                        Glide.with(binding.profileImg.getContext()).load(ds.child("childPhoto").getValue(String.class)).into(binding.profileImg);
                        binding.fullName.setText(ds.child("childName").getValue(String.class));
                        binding.email.setText(ds.child("parentEmail").getValue(String.class));
                        binding.childName.setText(ds.child("childName").getValue(String.class));
                        binding.childAge.setText(ds.child("childAge").getValue(String.class));
                        binding.parentName.setText(ds.child("parentName").getValue(String.class));
                        binding.parentEmail.setText(ds.child("parentEmail").getValue(String.class));
                        binding.newPass.setText(ds.child("newPassword").getValue(String.class));
                        binding.confirmPass.setText(ds.child("confPassword").getValue(String.class));
                        binding.childAddress.setText(ds.child("pAddress").getValue(String.class));
                        binding.Phone1.setText(ds.child("phone1").getValue(String.class));
                        binding.Phone2.setText(ds.child("phone2").getValue(String.class));
                        binding.medicState.setText(ds.child("medicState").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Unable to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}

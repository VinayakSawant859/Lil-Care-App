package com.example.lilcare.Fragments;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.lilcare.Adapters.chatAdapter;
import com.example.lilcare.Models.model;
import com.example.lilcare.R;
import com.example.lilcare.databinding.FragmentHomeBinding;
import com.example.lilcare.databinding.FragmentProfilePageBinding;
import com.example.lilcare.databinding.FragmentStatisticsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {
    FragmentStatisticsBinding binding;

    FirebaseDatabase firebaseDatabase;
    ArrayList<model> models;
    com.example.lilcare.Adapters.chatAdapter chatAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics,container,false);
        binding = FragmentStatisticsBinding.inflate(inflater,container,false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        models = new ArrayList<>();

        chatAdapter = new chatAdapter(getContext(),models);
        binding.chatRecycle.setAdapter(chatAdapter);

        firebaseDatabase.getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                models.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    model model = snapshot1.getValue(com.example.lilcare.Models.model.class);
                    models.add(model);
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}

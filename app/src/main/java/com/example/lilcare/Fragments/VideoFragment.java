package com.example.lilcare.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lilcare.R;
import com.example.lilcare.Models.fileModel;
import com.example.lilcare.Adapters.myVideoClass;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VideoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recView;
    myVideoClass videoClass;
    private String mParam1;
    private String mParam2;
    public VideoFragment() {
    }

    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        recView = (RecyclerView) view.findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<fileModel> options =
                new FirebaseRecyclerOptions.Builder<fileModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("myVideos"), fileModel.class)
                .build();

        videoClass = new myVideoClass(options);
        recView.setAdapter(videoClass);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        videoClass.startListening();
    }
}
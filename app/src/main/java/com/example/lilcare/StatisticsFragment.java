package com.example.lilcare;

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

public class StatisticsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView wel_text;
        LinearLayout cardlayout;

        View view = inflater.inflate(R.layout.fragment_statistics,container,false);


        wel_text = view.findViewById(R.id.wel_text);
        cardlayout = view.findViewById(R.id.cardlayout);
        cardlayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        CardView btn1= view.findViewById(R.id.expand);
        CardView btn2= view.findViewById(R.id.expand2);
        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int u = (wel_text.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;

                TransitionManager.beginDelayedTransition(cardlayout, new AutoTransition());
                wel_text.setVisibility(u);

            }
        });

        return view;
    }

}

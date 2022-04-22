package com.example.lilcare;

import android.animation.LayoutTransition;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView wel_text, wel_text2, wel_text3;
        LinearLayout cardlayout, cardlayout2, cardlayout3;



        View view = inflater.inflate(R.layout.fragment_home,container,false);
        //Expandable-Card
        wel_text = view.findViewById(R.id.wel_text);
        wel_text2 = view.findViewById(R.id.wel_text2);
        wel_text3 = view.findViewById(R.id.wel_text3);
        cardlayout = view.findViewById(R.id.cardlayout);
        cardlayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        cardlayout2 = view.findViewById(R.id.cardlayout2);
        cardlayout2.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        cardlayout3 = view.findViewById(R.id.cardlayout3);
        cardlayout3.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        //btn
        CardView btn1= view.findViewById(R.id.expand);
        CardView btn2= view.findViewById(R.id.expand2);
        CardView btn3= view.findViewById(R.id.expand3);
        btn1.setOnClickListener(new View.OnClickListener(){

                                   @Override
                                   public void onClick(View v) {
                                       int u = (wel_text.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;

                                       TransitionManager.beginDelayedTransition(cardlayout, new AutoTransition());
                                       wel_text.setVisibility(u);

                                   }
                               });
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int w = (wel_text2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;

                TransitionManager.beginDelayedTransition(cardlayout2, new AutoTransition());
                wel_text2.setVisibility(w);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int x = (wel_text3.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;

                TransitionManager.beginDelayedTransition(cardlayout3, new AutoTransition());
                wel_text3.setVisibility(x);

            }
        });



        return view;

    }


}




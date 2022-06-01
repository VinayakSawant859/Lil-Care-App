package com.example.lilcare.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lilcare.Fragments.HomeFragment;
import com.example.lilcare.Models.Message;
import com.example.lilcare.Models.model;
import com.example.lilcare.R;
import com.example.lilcare.databinding.RowConversationBinding;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.chatViewHolder>{

    Context context;
    ArrayList<model> models;

    public chatAdapter(Context context, ArrayList<model> models){
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_conversation,parent,false);
        return new chatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatViewHolder holder, int position) {
        model model  = models.get(position);

        holder.binding.username.setText(model.getParentName());
        Glide.with(context).load(model.getChildPhoto())
                .placeholder(R.drawable.ic_account)
                .into(holder.binding.dp);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeFragment.class);
                intent.putExtra("name", model.getParentName());
                intent.putExtra("child", model.getChildName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class chatViewHolder extends RecyclerView.ViewHolder{
        RowConversationBinding binding;

        public chatViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowConversationBinding.bind(itemView);
        }
    }
}


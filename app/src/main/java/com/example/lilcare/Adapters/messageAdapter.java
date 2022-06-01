package com.example.lilcare.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lilcare.Models.Message;
import com.example.lilcare.R;
import com.example.lilcare.databinding.ReceiveMsgBinding;
import com.example.lilcare.databinding.SendMsgBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class messageAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<Message> messages;
    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    public messageAdapter (Context context, ArrayList<Message> messages){
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SENT){
            View view = LayoutInflater.from(context).inflate(R.layout.send_msg,parent,false);
            return new senderViewHolder(view);
        }else
        {
            View view = LayoutInflater.from(context).inflate(R.layout.receive_msg, parent, false );
            return new receiverViewHolder(view);

        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message =messages.get(position);
        if (FirebaseAuth.getInstance().getUid().equals(message.getSenderID())){
            return ITEM_SENT;
        }else
        {
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (holder.getClass() == senderViewHolder.class){
            senderViewHolder viewHolder = (senderViewHolder) holder;
            viewHolder.sbinding.sendmsg.setText(message.getMessage());
        }
        else {
            receiverViewHolder viewHolder = (receiverViewHolder) holder;
            viewHolder.rbinding.recievemsg.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class senderViewHolder extends RecyclerView.ViewHolder{

        SendMsgBinding sbinding;
        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            sbinding = SendMsgBinding.bind(itemView);
        }
    }

    public class receiverViewHolder extends RecyclerView.ViewHolder{

        ReceiveMsgBinding rbinding;
        public receiverViewHolder(@NonNull View itemView) {
            super(itemView);
            rbinding = ReceiveMsgBinding.bind(itemView);
        }
    }
}


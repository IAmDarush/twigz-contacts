package com.example.twigzcontacts.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twigzcontacts.R;
import com.example.twigzcontacts.models.Message;

import java.util.ArrayList;

/**
 * Created by darush on 2/28/18.
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.MessageViewHolder>{

    private ArrayList<Message> mMessageList;

    public MessageRecyclerAdapter(ArrayList<Message> messageList) {
        mMessageList = messageList;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);

        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {

        final Message message = mMessageList.get(position);

        holder.tvName.setText(message.getName());
        holder.tvTime.setText(message.getTime());
        holder.tvOtp.setText(message.getOtp());

    }


    @Override
    public int getItemCount() {

        return mMessageList.size();

    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvTime;
        public TextView tvOtp;

        public MessageViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.text_itemmessage_name);
            tvTime = itemView.findViewById(R.id.text_itemmessage_time);
            tvOtp = itemView.findViewById(R.id.text_itemmessage_otp);

        }
    }


}

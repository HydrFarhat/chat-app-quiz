package com.farhat.chatapp.ui.detailedchat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.farhat.chatapp.R;
import com.farhat.chatapp.data.Message;
import com.farhat.chatapp.ui.detailedchat.IDetailedChat;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private final IDetailedChat.Presenter presenter;


    public MessagesAdapter(IDetailedChat.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_self_message,parent,false));
        }else{
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_other_message,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {
        presenter.bindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return presenter.messagesSize();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements IDetailedChat.ViewHolder{
        private final TextView tvMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
        }

        @Override
        public void setMessage(String message) {
            tvMessage.setText(message);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return presenter.getViewType(position);
    }
}

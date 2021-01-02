package com.farhat.chatapp.ui.chats.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.farhat.chatapp.R;
import com.farhat.chatapp.ui.chats.IChats;
import com.squareup.picasso.Picasso;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    private final IChats.Presenter presenter;

    public ChatsAdapter(IChats.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_chat,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        presenter.bindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return presenter.chatsSize();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements IChats.ViewHolder{
        private final TextView tvName;
        private final TextView tvLatestMessage;
        private final TextView tvTimeStamp;
        private final ImageView imUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvLatestMessage = itemView.findViewById(R.id.tv_latest_message);
            tvTimeStamp = itemView.findViewById(R.id.tv_timestamp);
            imUser = itemView.findViewById(R.id.imUser);
        }

        @Override
        public void setName(String name) {
            tvName.setText(name);

        }

        @Override
        public void setMessage(String message) {
            tvLatestMessage.setText(message);
        }

        @Override
        public void setTimeStamp(String timeStamp) {
            tvTimeStamp.setText(timeStamp);

        }

        @Override
        public void setImage(String image) {
            Picasso.get().load(image).into(imUser);
        }

        @Override
        public void setClickListener(View.OnClickListener listener) {
            itemView.setOnClickListener(listener);
        }

        @Override
        public void setVisibilityOfLatestMessageAndTimeStamp(int gone) {
            tvTimeStamp.setVisibility(gone);
            tvLatestMessage.setVisibility(gone);
        }
    }
}

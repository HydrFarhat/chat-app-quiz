package com.farhat.chatapp.ui.detailedchat;

import com.farhat.chatapp.ui.chats.IChats;

import java.util.Date;

public interface IDetailedChat{

    interface ViewHolder{
        void setMessage(String name);
    }

    interface View{
        void setMessages();
        void refreshChat();

    }

    interface Presenter{
        void loadMessages(int user_id,int other);
        void sendMessage(String content,int user_id);
        void bindViewHolder(IDetailedChat.ViewHolder viewHolder, int position);
        int messagesSize();
        int getViewType(int position);

    }
}
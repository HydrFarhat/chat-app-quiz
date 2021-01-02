package com.farhat.chatapp.ui.chats;

import android.view.View;

import com.farhat.chatapp.data.User;

import java.util.List;

public interface IChats{
    interface ViewHolder{
        void setName(String name);
        void setMessage(String name);
        void setTimeStamp(String name);
        void setImage(String image);
        void setClickListener(android.view.View.OnClickListener listener);
        void setVisibilityOfLatestMessageAndTimeStamp(int gone);

    }
    interface View{
        void showDetailedChat(int userId,String username);
        void setChats();
    }

    interface Presenter{
        void loadChats();
        void detachView();
        void bindViewHolder(IChats.ViewHolder viewHolder,int position);
        int chatsSize();
    }

}
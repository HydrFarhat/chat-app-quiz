package com.farhat.chatapp.ui.chats;

import android.view.View;

import com.farhat.chatapp.data.User;
import com.farhat.chatapp.repository.IRepository;
import com.farhat.chatapp.repository.Repo;

import java.util.List;

public class ChatPresenter  implements  IChats.Presenter{
    private List<User> users;
    private IChats.View view;

    public ChatPresenter(IChats.View view) {
        this.view = view;
    }

    @Override
    public void loadChats() {
        IRepository repository = new Repo();
        users = repository.loadUsers();
        assert view !=null;
        view.setChats();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void bindViewHolder(IChats.ViewHolder viewHolder,int pos) {
        assert view !=null && viewHolder!=null;
        User user = users.get(pos);
        if(user.getLatestMessage() !=null) {
            viewHolder.setVisibilityOfLatestMessageAndTimeStamp(View.VISIBLE);
            viewHolder.setMessage(user.getLatestMessage().getContent());
            viewHolder.setTimeStamp(user.getLatestMessage().getDate().toString());
        }else{
            viewHolder.setVisibilityOfLatestMessageAndTimeStamp(View.GONE);
        }

        viewHolder.setName(user.getName());
        viewHolder.setImage(user.getImage());
        viewHolder.setClickListener(v -> {
            view.showDetailedChat(user.getId(),user.getName());
        });
    }

    @Override
    public int chatsSize() {
        return users == null ? 0 : users.size();
    }
}

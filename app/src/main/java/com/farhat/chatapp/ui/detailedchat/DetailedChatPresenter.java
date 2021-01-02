package com.farhat.chatapp.ui.detailedchat;

import android.os.Handler;

import com.farhat.chatapp.data.Message;
import com.farhat.chatapp.repository.IRepository;
import com.farhat.chatapp.repository.Repo;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class DetailedChatPresenter implements IDetailedChat.Presenter {

    private List<Message> messagesList;
    private final IDetailedChat.View view;
    IRepository repo  = new Repo();

    public DetailedChatPresenter(IDetailedChat.View view) {
        this.view = view;
    }

    @Override
    public void loadMessages(int user_id, int other) {
        messagesList = repo.loadMessages(user_id,other);
        assert messagesList!=null;
        view.setMessages();
    }

    @Override
    public void sendMessage(String content,int user_id) {
        Random generator = new Random();
        int number = generator.nextInt(6);
        long result = number * 10;

        int number1 = generator.nextInt(6);
        long result1 = number1 * 10;


        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Message message = new Message();
            message.setContent(content);
            message.setUser_id(user_id);
            message.setDate(new Date());
            message.setFor_user(222);
            repo.insertMessage(message);
            messagesList.add(message) ;
            view.refreshChat();
        }, result);


        final Handler handler2 = new Handler();
        handler2.postDelayed(() -> {
            Message message2 = new Message();
            message2.setContent(content);
            message2.setUser_id(222);
            message2.setDate(new Date());
            message2.setFor_user(user_id);
            repo.insertMessage(message2);
            messagesList.add(message2) ;
            view.refreshChat();
        }, result1);

    }

    @Override
    public void bindViewHolder(IDetailedChat.ViewHolder viewHolder, int position) {
        viewHolder.setMessage(messagesList.get(position).getContent()+"\n\n"+messagesList.get(position).getDate().toString());
    }

    @Override
    public int messagesSize() {
        return messagesList == null ? 0 : messagesList.size();
    }

    @Override
    public int getViewType(int position) {
        if(messagesList.get(position).getUser_id() == 222){
            return 0;
        }else{
            return 1;
        }

    }
}

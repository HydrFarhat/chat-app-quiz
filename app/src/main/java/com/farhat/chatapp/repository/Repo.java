package com.farhat.chatapp.repository;

import com.farhat.chatapp.core.MyApp;
import com.farhat.chatapp.data.Message;
import com.farhat.chatapp.data.User;
import com.farhat.chatapp.data.room.AppDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repo implements IRepository {

    AppDatabase appDatabase;

    public Repo() {
        appDatabase = AppDatabase.getAppDatabase(MyApp.getAppContext());
    }

    @Override
    public List<User> loadUsers() {
        List<User> users = appDatabase.usersDao().getAllUsers();
        List<User> usersThatSent = new ArrayList<User>();

        for(User user : users){
            user.setLatestMessage(getLatestMessage(user.getId()));
            if(user.getLatestMessage() !=null && user.getId() != 222)
                usersThatSent.add(user);
        }
        for(User user : usersThatSent){
            users.remove(user);
        }
        Collections.sort(usersThatSent);
         usersThatSent.addAll(users);
        return usersThatSent;
    }

    @Override
    public List<Message> loadMessages(int self, int user_id) {
        return appDatabase.messagesDao().findMessagesBetween(self,user_id);
    }

    @Override
    public void insertMessage(Message message) {
        appDatabase.messagesDao().insertMessage(message);
    }

    @Override
    public void insertUsers(List<User> users) {
        appDatabase.usersDao().insertAll(users);
    }

    @Override
    public Message getLatestMessage(int user_id) {
        return appDatabase.messagesDao().getLatestMessageOf(user_id);
    }
}

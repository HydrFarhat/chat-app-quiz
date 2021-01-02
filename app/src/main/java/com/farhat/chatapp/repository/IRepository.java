package com.farhat.chatapp.repository;

import com.farhat.chatapp.data.Message;
import com.farhat.chatapp.data.User;

import java.util.List;

public interface IRepository{
    List<User> loadUsers();
    List<Message> loadMessages(int self,int user_id);
    void insertMessage(Message message);
    void insertUsers(List<User> user);

    Message getLatestMessage(int user_id);
}

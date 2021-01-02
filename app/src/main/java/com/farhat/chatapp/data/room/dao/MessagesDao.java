package com.farhat.chatapp.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.farhat.chatapp.data.Message;
import com.farhat.chatapp.data.User;

import java.util.List;

@Dao
public interface MessagesDao {

    @Query("SELECT * FROM messages WHERE user_id = :self  AND for_user = :other OR user_id = :other  AND for_user = :self ")
    List<Message> findMessagesBetween(int self,int other);

    @Query("SELECT * FROM messages WHERE user_id = :user ORDER BY date DESC LIMIT 1 ")
    Message getLatestMessageOf(int user);

    @Insert
    void insertMessage(Message msg);


}
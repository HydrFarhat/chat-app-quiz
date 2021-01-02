package com.farhat.chatapp.data.room.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.farhat.chatapp.data.Message;
import com.farhat.chatapp.data.User;

import java.util.List;

@Dao
public interface UsersDao{

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Insert
    void insertUser(User user);

    @Insert
    void insertAll(List<User> users);

}
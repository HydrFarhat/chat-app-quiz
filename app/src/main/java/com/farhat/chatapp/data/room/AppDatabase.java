package com.farhat.chatapp.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.farhat.chatapp.data.Message;
import com.farhat.chatapp.data.User;
import com.farhat.chatapp.data.room.dao.MessagesDao;
import com.farhat.chatapp.data.room.dao.UsersDao;

@Database(entities = {Message.class, User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract MessagesDao messagesDao();
    public abstract UsersDao usersDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class,
                    "chats-db")

                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
package com.farhat.chatapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.farhat.chatapp.core.DateConverter;

import java.util.Date;



@Entity(tableName = "messages")
@TypeConverters(DateConverter.class)
public class Message {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "date")
    private Date date;
    @ColumnInfo(name = "user_id")
    private int user_id;
    @ColumnInfo(name = "for_user")
    private int for_user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFor_user() {
        return for_user;
    }

    public void setFor_user(int for_user) {
        this.for_user = for_user;
    }
}

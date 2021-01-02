package com.farhat.chatapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import com.farhat.chatapp.data.User;
import com.farhat.chatapp.repository.IRepository;
import com.farhat.chatapp.repository.Repo;
import com.farhat.chatapp.ui.chats.ChatFragment;
import com.farhat.chatapp.ui.detailedchat.DetailedChatFragment;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements IChatsAndDetails {


    private SharedPreferences shr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Chat App");

        shr = getSharedPreferences("chat",MODE_PRIVATE);
        if(!shr.getBoolean("init",false)){
            setupData();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout,new ChatFragment()).commit();

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    private void setupData() {
        Faker faker = new Faker();
        IRepository rep = new Repo();
        List<User> users = new ArrayList<User>();
        List<String> generatedNames = new ArrayList<>();
        for(int i =0 ;i<200;i++){
            String image;
            if(i%2==0){
                image = "https://pbs.twimg.com/profile_images/1284783657597784064/yLOsvGoN_400x400.jpg";
            }else{
                image = "https://qph.fs.quoracdn.net/main-qimg-c289d20e27d752e72755c2856fdadbff";
            }
            String name = faker.name().name();
            while(generatedNames.contains(name)){
                name = faker.name().name();
            }
           users.add(new User(i,name,image));
        }
        users.add(new User(222,"ME","https://pbs.twimg.com/profile_images/1284783657597784064/yLOsvGoN_400x400.jpg"));
        rep.insertUsers(users);
        shr.edit().putBoolean("init",true).apply();
    }

    @Override
    public void navigateToChatWithId(int userId,String username) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(username);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        Log.d("zaza","zaza"+userId);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, DetailedChatFragment.newInstance(userId)).addToBackStack("DCF"+userId).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportActionBar().setTitle("Chat App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(null);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
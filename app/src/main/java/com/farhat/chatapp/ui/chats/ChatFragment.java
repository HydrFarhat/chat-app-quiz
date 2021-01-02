package com.farhat.chatapp.ui.chats;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farhat.chatapp.IChatsAndDetails;
import com.farhat.chatapp.R;
import com.farhat.chatapp.ui.chats.adapter.ChatsAdapter;

public class ChatFragment extends Fragment implements IChats.View{

    private IChatsAndDetails iChatsAndDetails;
    private IChats.Presenter presenter;
    protected View mView;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       inflater.inflate(R.layout.fragment_chat, container, false);
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        presenter = new ChatPresenter(this);
        presenter.loadChats();
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IChatsAndDetails){
            iChatsAndDetails = (IChatsAndDetails)context;
        }else{
            throw new Error("Must Implement IChatsAndDetails");
        }
    }

    @Override
    public void showDetailedChat(int userId,String username) {
        iChatsAndDetails.navigateToChatWithId(userId,username);
    }

    @Override
    public void setChats() {
        RecyclerView v = mView.findViewById(R.id.rv_users);
        ChatsAdapter adapter = new ChatsAdapter(presenter);
        v.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(requireContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        v.setLayoutManager(llm);
    }

    @Override
    public void onPause() {
        presenter.detachView();
        super.onPause();
    }
}
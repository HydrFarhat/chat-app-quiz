package com.farhat.chatapp.ui.detailedchat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.farhat.chatapp.R;
import com.farhat.chatapp.ui.chats.IChats;
import com.farhat.chatapp.ui.chats.adapter.ChatsAdapter;
import com.farhat.chatapp.ui.detailedchat.adapter.MessagesAdapter;


public class DetailedChatFragment extends Fragment implements IDetailedChat.View {

    private static final String ARG_PARAM1 = "user_id";
    protected View mView;
    private int user_id;
    private IDetailedChat.Presenter presenter;
    private  RecyclerView v;
    public DetailedChatFragment() {
        // Required empty public constructor
    }


    public static DetailedChatFragment newInstance(int user_id) {
        DetailedChatFragment fragment = new DetailedChatFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, user_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user_id = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed_chat, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        presenter = new DetailedChatPresenter(this);
        presenter.loadMessages(222,user_id);

        Button btnSend = view.findViewById(R.id.btnSend);
        EditText edtMessage = view.findViewById(R.id.edtMessage);

        btnSend.setOnClickListener(v -> {
            String message = edtMessage.getText().toString().trim();
            if(!message.trim().isEmpty()) {
                edtMessage.getText().clear();
                presenter.sendMessage(message, user_id);
            }
        });
    }

    @Override
    public void setMessages() {
        v = mView.findViewById(R.id.rv_messages);
        MessagesAdapter adapter = new MessagesAdapter(presenter);
        v.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(requireContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        v.setLayoutManager(llm);
        llm.scrollToPosition(presenter.messagesSize()-1);
    }

    @Override
    public void refreshChat() {
        assert v.getAdapter() !=null && v.getLayoutManager() !=null;
        v.getAdapter().notifyDataSetChanged();
        v.getLayoutManager().scrollToPosition(presenter.messagesSize()-1);

    }
}
package chat.chatmvvm.models;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import chat.chatmvvm.interfaces.ModelCallBacks;

/**
 */

public class MessageModel {
    private ArrayList<ChatPojo> mMessages;

    public void addMessages(DataSnapshot dataSnapshot, ModelCallBacks callBacks){
        if (mMessages==null){
            mMessages= new ArrayList<>();
        }
        ChatPojo chatPojo=new ChatPojo(dataSnapshot);
        mMessages.add(chatPojo);
        callBacks.onModelUpdated(mMessages);
    }
}

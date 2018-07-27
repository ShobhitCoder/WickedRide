package chat.chatmvvm.interfaces;

import java.util.ArrayList;

import chat.chatmvvm.models.ChatPojo;

/**
 */

public interface ModelCallBacks {
    void onModelUpdated(ArrayList<ChatPojo> messages);
}

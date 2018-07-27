package chat.chatmvvm.interfaces;

/**
 */

public interface Observer<T> {
    void onObserve(int event, T eventMessage);
}

package chat.chatmvvm.views;

/***/

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import chat.chatmvvm.BR;
import chat.chatmvvm.R;
import chat.chatmvvm.databinding.RowChatAdapterBinding;
import chat.chatmvvm.models.ChatPojo;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.BindingHolder> {


    private ArrayList<ChatPojo> chatList;
    private Context mContext;

    public ChatAdapter(Context context, ArrayList<ChatPojo> chatList) {
        this.chatList =chatList;
        this.mContext =context;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.row_chat_adapter, parent, false);
        RowChatAdapterBinding binding=DataBindingUtil.bind(view);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(final BindingHolder holder, final int position) {
        holder.getBinding().setVariable(BR.chatMessage, chatList.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }


    class BindingHolder extends RecyclerView.ViewHolder {

        private RowChatAdapterBinding binding;

        BindingHolder(RowChatAdapterBinding binding) {
            super(binding.getRoot());
            setBinding(binding);
        }

        public void setBinding(RowChatAdapterBinding binding) {
            this.binding = binding;
        }

        public RowChatAdapterBinding getBinding() {
            return binding;
        }
    }
}

package com.jambit.kchat.android.ui.chatlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jambit.kchat.android.databinding.ChatListItemViewBinding
import com.jambit.kchat.model.Chat

private const val TAG = "ChatListAdapter"

class ChatListAdapter(private val onChatSelected: (String) -> Unit) : ListAdapter<Chat, ChatListAdapter.ViewHolder>(ChatDiffCallback) {

    class ViewHolder(private val binding: ChatListItemViewBinding, private val onChatSelected: (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.container.setOnClickListener {
                binding.chat?.uuid?.let {
                    onChatSelected.invoke(it)
                }
            }
        }

        fun bind(chat: Chat) {
            this.binding.chat = chat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChatListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onChatSelected)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object ChatDiffCallback : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat) = oldItem.uuid == newItem.uuid
    override fun areContentsTheSame(oldItem: Chat, newItem: Chat) = oldItem.title == newItem.title
}
package com.jambit.kchat.android.ui.chatlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jambit.kchat.android.R
import com.jambit.kchat.android.databinding.ChatListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.DividerItemDecoration


class ChatListFragment : Fragment() {

    private lateinit var binding: ChatListFragmentBinding
    private val vm: ChatListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChatListFragmentBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        binding.recyclerView.addItemDecoration(dividerItemDecoration)

        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChatListAdapter { chatUuid ->
            findNavController().navigate(
                ChatListFragmentDirections.actionChatListFragmentToChatFragment(
                    chatUuid
                )
            )
        }
        binding.recyclerView.adapter = adapter
        vm.chats.observe(viewLifecycleOwner, adapter::submitList)
    }

}
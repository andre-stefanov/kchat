package com.jambit.kchat.android.ui.chatlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jambit.kchat.android.utils.logd
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jambit.kchat.android.databinding.ChatListFragmentBinding
import com.jambit.kchat.android.utils.StringPreference
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class ChatListFragment : Fragment() {

    private lateinit var binding: ChatListFragmentBinding
    private val vm: ChatListViewModel by viewModel()

    private val appContext: Context by inject()
    private var username: String by StringPreference(appContext, "username", "NONE")

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

        binding.usernameButton.setOnClickListener {
            username = "John ${System.currentTimeMillis()}"
        }

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
        vm.chats.observe(viewLifecycleOwner, {
            logd(it.isEmpty()) {
                "Chat list is empty"
            }
            adapter.submitList(it)
        })
    }

}
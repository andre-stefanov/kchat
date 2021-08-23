package com.jambit.kchat.android.ui.chatlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jambit.kchat.android.databinding.ChatListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatListFragment : Fragment() {

    private lateinit var binding: ChatListFragmentBinding
    private val vm: ChatListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChatListFragmentBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(ChatListAdapter()) {
            binding.recyclerView.adapter = this
            vm.chats.observe(viewLifecycleOwner, this::submitList)
        }
    }

}
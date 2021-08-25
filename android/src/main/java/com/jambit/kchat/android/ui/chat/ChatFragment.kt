package com.jambit.kchat.android.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jambit.kchat.android.databinding.ChatFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment() {

    private val args: ChatFragmentArgs by navArgs()
    private val vm: ChatViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ChatFragmentBinding.inflate(inflater, container, false)

        binding.textView.text = args.uuid

        return binding.root
    }

}
package com.jambit.kchat.android.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jambit.kchat.android.databinding.ChatFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment() {

    private val args: ChatFragmentArgs by navArgs()
    private val vm: ChatViewModel by viewModel()

    private val appContext: Context by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ChatFragmentBinding.inflate(inflater, container, false)

        binding.textView.text = "USERNAME"

        return binding.root
    }

}
package com.jambit.kchat.model

import kotlinx.serialization.Serializable

@Serializable
data class Chat(val uuid: String, val title: String)

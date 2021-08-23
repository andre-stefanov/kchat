package com.jambit.kchat.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(val from: String, val text: String)

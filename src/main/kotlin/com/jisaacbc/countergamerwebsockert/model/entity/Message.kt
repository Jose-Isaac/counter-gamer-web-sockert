package com.jisaacbc.countergamerwebsockert.model.entity

import com.jisaacbc.countergamerwebsockert.model.MessageType
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val type: MessageType,
    val name: String,
    val countValue: Int? = null
)
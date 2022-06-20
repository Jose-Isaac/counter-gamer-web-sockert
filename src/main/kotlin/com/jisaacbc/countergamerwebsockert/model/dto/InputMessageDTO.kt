package com.jisaacbc.countergamerwebsockert.model.dto

import com.jisaacbc.countergamerwebsockert.model.MessageType
import kotlinx.serialization.Serializable

@Serializable
data class InputMessageDTO(
    val type: MessageType,
    val name: String,
    val text: String? = null
)

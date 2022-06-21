package com.jisaacbc.countergamerwebsockert.model.dto

import com.jisaacbc.countergamerwebsockert.model.MessageType
import com.jisaacbc.countergamerwebsockert.model.entity.Message
import kotlinx.serialization.Serializable

@Serializable
data class InputMessageDTO(
    val type: MessageType,
    val name: String,
) {
    fun toMessage() = Message(
        type = this.type,
        name = this.name
    )
}

package com.jisaacbc.countergamerwebsockert.handler

import com.jisaacbc.countergamerwebsockert.model.dto.InputMessageDTO
import com.jisaacbc.countergamerwebsockert.model.MessageType
import com.jisaacbc.countergamerwebsockert.model.User
import com.jisaacbc.countergamerwebsockert.model.entity.Message
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.atomic.AtomicLong

class CountHandler : TextWebSocketHandler() {
    private val sessionList = HashMap<WebSocketSession, User>()
    private val uids = AtomicLong(0)
    private var count = 0;

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionList -= session
    }

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("received connection")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val msg = Json.decodeFromString<InputMessageDTO>(message.payload).toMessage()

        when(msg.type) {
            MessageType.JOIN -> {
                val user = User(uids.getAndIncrement(), msg.name)
                sessionList[session] = user
                emit(session, msg.copy(countValue = count))
            }
            MessageType.INCREMENT -> {
                count++
                broadcast(msg.copy(countValue = count))
            }
            else -> {
                println("Invalid type, type not found!")
            }
        }
        println(msg)
    }

    private fun emit(session: WebSocketSession, message: Message) {
        return session.sendMessage(
            TextMessage(
                Json.encodeToString(message)
            )
        )
    }

    private fun broadcast(msg: Message) = sessionList.forEach { emit(it.key, msg) }
    private fun broadcastToOthers(me: WebSocketSession, msg: Message) =
        sessionList.filterNot { it.key == me }.forEach { emit(it.key, msg) }
}
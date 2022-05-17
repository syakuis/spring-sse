package io.github.syakuis.application

import org.springframework.stereotype.Service

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-17
 */
@Service
class ChatService(val chatEventManager: ChatEventManager) {
    fun publisher(message: String) {
        chatEventManager.sender(message)
    }
}
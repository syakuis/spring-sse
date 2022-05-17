package io.github.syakuis.application

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-17
 */
@RestController
@RequestMapping("/sse/v1/chat-message")
class ChatSsrController(val chatEventManager: ChatEventManager) {

    /**
     * test: curl -N http://localhost:8080/sse/v1/chat-message
     */
    @GetMapping
    fun consumer(): SseEmitter {
        return chatEventManager.receiver()
    }


}
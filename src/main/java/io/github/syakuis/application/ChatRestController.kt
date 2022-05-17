package io.github.syakuis.application

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-17
 */
@RestController
@RequestMapping("/api/v1/chat-message")
class ChatRestController(val chatService: ChatService) {

    @PostMapping
    fun message(@RequestBody message: String): ResponseEntity<Void> {
        chatService.publisher(message)
        return ResponseEntity.ok().build()
    }
}
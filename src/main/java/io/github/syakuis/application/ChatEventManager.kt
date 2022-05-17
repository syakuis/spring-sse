package io.github.syakuis.application

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-17
 */
@Component
class ChatEventManager {
    companion object {
        val log: Logger = LoggerFactory.getLogger(ChatEventManager::class.java)
        val stores : MutableSet<SseEmitter> = hashSetOf()
    }

    fun receiver(): SseEmitter {
        val sseEmitter: SseEmitter = SseEmitter()

        stores.add(sseEmitter)

        sseEmitter.onCompletion { stores.remove(sseEmitter) }
        sseEmitter.onTimeout { stores.remove(sseEmitter) }

        return sseEmitter
    }

    fun sender(message: String) {
        try {
            stores.forEach { sseEmitter ->
                sseEmitter.send(message)
                sseEmitter.onError { error ->
                    sseEmitter.completeWithError(error)
                    stores.remove(sseEmitter)
                }
            }
        } catch (e: IOException) {
            log.warn(e.message, e)
        }
    }
}
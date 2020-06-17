package com.github.ftfetter.lead.service

import com.github.ftfetter.lead.model.WebMessage
import com.github.ftfetter.lead.repository.MessageRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class MessageService(val messageRepository: MessageRepository) {

    fun saveMessage(webMessage: WebMessage): Mono<Boolean> {

        val payload = webMessage as HashMap<String, Any>
       return true.toMono()

//        return messageRepository
//                .findById(message.id)
//                .map { changeRetryCount(it, messageState) }
//                .switchIfEmpty(Mono.just(1))
//                .flatMap { retryMessageRepository.save(it) }
//                .logOnSuccess("Retry Message is saved in db", mapOf("service" to message.serviceName, "productCode" to productCode))
//                .flatMap { dlqEvent -> publishToDLQ(message, dlqEvent) }
//                .thenReturn(true)
//                .onErrorReturn(false)
    }

}
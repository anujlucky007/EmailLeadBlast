package com.github.ftfetter.lead.service

import com.github.ftfetter.lead.model.MessageDetails
import com.github.ftfetter.lead.model.Status
import com.github.ftfetter.lead.model.WebMessage
import com.github.ftfetter.lead.repository.MessageRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.switchIfEmpty
import java.util.*

@Service
class MessageService(val messageRepository: MessageRepository) {

    fun saveMessage(webMessage: WebMessage): Mono<Boolean> {

        return messageRepository.findByCustomerMobileNumberAndStatus(webMessage.contact.uid, Status.NOT_PROCESSED)
                .switchIfEmpty {
                    messageRepository.save(MessageDetails(uuid = UUID.randomUUID(),customerMobileNumber = webMessage.contact.uid, contact = webMessage.contact, status = Status.NOT_PROCESSED))
                }.map {
                    it.messageList.add(webMessage.message)
                    it
                }.flatMap {
                    messageRepository.save(it)
                }.map {
                    true
                }
    }

}
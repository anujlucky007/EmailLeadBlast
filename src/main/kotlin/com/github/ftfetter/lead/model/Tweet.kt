package com.github.ftfetter.lead.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Document("messages")
class MessageDetails( val customerMobileNumber: String, val contact: Contact, val messageList: List<Message>, val lastReceivedMessageTime: Long,
                  var status:Status
)

enum class Status {
    NOT_PROCESSED,
    COMPLETED,
    DLQ
}

data class WebMessage(
        val uid: String,
        val contact: Contact,
        val message: Message
)

data class Contact(
        val uid: String,
        val name: String,
        val type: String? = null
)


data class Message(
        val dtm: String,
        val uid: String,
        val cuid: String? = null,
        val dir: String? = null,
        val type: String? = null,
        val body: String? = null
)
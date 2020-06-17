package com.github.ftfetter.lead.repository

import com.github.ftfetter.lead.model.MessageDetails
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.mongodb.repository.Tailable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface MessageRepository : ReactiveMongoRepository<MessageDetails, String> {

    @Tailable
    fun findWithTailableCursorBy(): Flux<MessageDetails>
}
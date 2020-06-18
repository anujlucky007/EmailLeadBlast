package com.github.ftfetter.lead.repository

import com.github.ftfetter.lead.model.MessageDetails
import com.github.ftfetter.lead.model.Status
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.mongodb.repository.Tailable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface MessageRepository : ReactiveMongoRepository<MessageDetails, String> {

    @Tailable
    fun findWithTailableCursorBy(): Flux<MessageDetails>

    fun findByCustomerMobileNumberAndStatus(customerMobileNumber : String,status: Status): Mono<MessageDetails>
    fun findByStatusAndLastReceivedMessageTimeLessThan( status: Status,nextExecutionTime: Long): Flux<MessageDetails>
}
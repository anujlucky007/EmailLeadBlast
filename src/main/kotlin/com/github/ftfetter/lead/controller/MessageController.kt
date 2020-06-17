package com.github.ftfetter.lead.controller

import com.github.ftfetter.lead.model.WebMessage
import com.github.ftfetter.lead.repository.MessageRepository
import com.github.ftfetter.lead.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/message")
class MessageController(val messageRepository: MessageRepository,val messageService: MessageService) {

    @GetMapping
    fun getAllTweets() = messageRepository.findAll()

    @PostMapping
    fun saveMessage(@Valid @RequestBody webMessage: WebMessage) = messageService.saveMessage(webMessage)

    @GetMapping("/{id}")
    fun getMessageByMobileNumber(@PathVariable("id") customerMobileNumber: String) = messageRepository.findById(customerMobileNumber)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

}
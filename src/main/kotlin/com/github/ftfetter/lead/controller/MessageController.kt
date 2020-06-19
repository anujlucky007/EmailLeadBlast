package com.github.ftfetter.lead.controller

import com.github.ftfetter.lead.repository.MessageRepository
import com.github.ftfetter.lead.service.MessageService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import org.synchronoss.cloud.nio.multipart.MultipartUtils.getHeaders
import reactor.core.Disposable
import reactor.core.publisher.Mono
import java.util.*


@RestController
@RequestMapping("/message")
class MessageController(val messageRepository: MessageRepository,val messageService: MessageService) {

    @GetMapping
    fun getAllTweets() = messageRepository.findAll()

    @RequestMapping(value = ["/whatsapp"], method = [RequestMethod.POST],consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun saveMessage(exchange: ServerWebExchange ): Mono<HashMap<String, Any>> {
        val form = HashMap<String, Any>()
        return  exchange.getFormData().map{
             map ->
            for ((key, value1) in map.entries) {
                for (value in value1) {
                    form[key] = value
                }
            }
            println(form)
            form
        }.map{
            it
        }
       // messageService.saveMessage(WebMessage("11", Contact("11","1","1"), Message("","","","")))
    }

    @GetMapping("/{id}")
    fun getMessageByMobileNumber(@PathVariable("id") customerMobileNumber: String) = messageRepository.findById(customerMobileNumber)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

}
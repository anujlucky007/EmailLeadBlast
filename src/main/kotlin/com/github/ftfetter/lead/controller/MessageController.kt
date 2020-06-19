package com.github.ftfetter.lead.controller

import com.github.ftfetter.lead.model.Contact
import com.github.ftfetter.lead.model.Message
import com.github.ftfetter.lead.model.WebMessage
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
import javax.validation.Valid


@RestController
@RequestMapping("/message")
class MessageController(val messageRepository: MessageRepository,val messageService: MessageService) {

    @GetMapping
    fun getAllTweets() = messageRepository.findAll()

    @RequestMapping(value = ["/whatsapp"], method = [RequestMethod.POST],consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun saveMessage(exchange: ServerWebExchange ): Mono<Boolean> {
        val form = HashMap<String, String>()
        return  exchange.formData.map{
             map ->
            for ((key, value1) in map.entries) {
                for (value in value1) {
                    form[key] = value
                }
            }
            println(form)
            form
        }.map{
            val contact = Contact(uid = it["contact[uid]"].toString(),name = it["contact[name]"].toString(),type=it["contact[type]"].toString())
            val message = Message(dtm=it["message[dtm]"]!!,cuid= it["message[cuid]"],uid=it["message[uid]"]!!,dir=it["message[dir]"],type=it["message[type]"],body = it["message[body][text]"])
            WebMessage(contact =contact,message =  message,uid = it["uid"].toString())
        }.flatMap {
            messageService.saveMessage(it)
        }
    }


    @PostMapping(value = ["/whatsapp/json"],consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun saveMessage(@Valid @RequestBody webMessage: WebMessage) = messageService.saveMessage(webMessage)
    @GetMapping("/{id}")
    fun getMessageByMobileNumber(@PathVariable("id") customerMobileNumber: String) = messageRepository.findById(customerMobileNumber)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

}
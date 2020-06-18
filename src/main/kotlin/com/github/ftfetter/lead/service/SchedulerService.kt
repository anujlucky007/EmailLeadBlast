package com.github.ftfetter.lead.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component



@Component
class SchedulerService(
        val messageService: MessageService
) {


    @Scheduled(fixedRate = 20000)
    fun publish() {
        val diff : Long =100000
        val lastEmailTime: Long = System.currentTimeMillis() - diff
        messageService.processMessage(lastEmailTime).subscribe()
    }


}
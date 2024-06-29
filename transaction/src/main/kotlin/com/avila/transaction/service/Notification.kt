package com.avila.transaction.service

import com.avila.transaction.model.Transaction

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties

import org.springframework.messaging.handler.annotation.Payload

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@ConfigurationProperties("queue.notification")
@Component object TransactionQueue {
    lateinit var name: String
}

data class Notification (
    val success: Boolean
)

@Service class NotificationService {

    @Autowired
    private lateinit var template: RabbitTemplate

    fun notify(transaction: Transaction) {
        template.convertAndSend(TransactionQueue.name, transaction)
    }

}

@Component class NotificationListener {

    @RabbitListener(queues = ["\${queue.notification.name}"])
    fun listen(@Payload notification: Notification) {
        if (!notification.success) {
            // TODO: Unstable notification service error
            return
        }
    }

}

@Configuration class RabbitMqConfiguration {

    @Bean fun publishParser() = Jackson2JsonMessageConverter()

    @Bean fun publishQueue() = Queue(TransactionQueue.name)

}

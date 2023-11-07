package ru.taratonov.audit.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import ru.taratonov.audit.model.AuditAction


@Configuration
class RedisConfig(@Value("\${spring.data.redis.host}") val name: String) {

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val configuration = RedisStandaloneConfiguration()
        configuration.hostName = name
        return JedisConnectionFactory(configuration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, AuditAction> {
        val redisTemplate = RedisTemplate<String, AuditAction>()
        redisTemplate.connectionFactory = jedisConnectionFactory()
        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(AuditAction::class.java)

        return redisTemplate
    }
}
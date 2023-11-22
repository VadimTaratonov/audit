package ru.taratonov.audit.config

import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import io.lettuce.core.RedisClient
import io.lettuce.core.api.coroutines
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    private val host: String,
    @Value("\${spring.data.redis.port}")
    private val port: String
) {
    @Bean
    @OptIn(ExperimentalLettuceCoroutinesApi::class)
    fun reactiveLettuceRedisConnection(): RedisCoroutinesCommands<String, String> {
        val redisClient: RedisClient = RedisClient.create("redis://$host:$port")
        return redisClient.connect().coroutines()
    }
}
package com.djoumoi.librarymanager

import com.intuit.karate.junit5.Karate
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.math.BigInteger

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KarateTest {

    companion object {
        @Container
        @JvmStatic
        val mongo = MongoDBContainer("mongo:6.0.10")



        @JvmStatic
        @DynamicPropertySource
        fun setProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri", mongo::getReplicaSetUrl)
        }
    }
    @LocalServerPort
    lateinit var localServerPort: BigInteger

    @Karate.Test
    fun test(): Karate? {
        return Karate.run("classpath:features")
            .systemProperty("server.url", "http://localhost:${localServerPort}")
    }
}
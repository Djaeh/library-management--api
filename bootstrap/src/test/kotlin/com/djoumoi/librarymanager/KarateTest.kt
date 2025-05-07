package com.djoumoi.librarymanager

import com.intuit.karate.junit5.Karate
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import java.math.BigInteger


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KarateTest {

    @LocalServerPort
    lateinit var localServerPort: BigInteger

    @Karate.Test
    fun test(): Karate? {
        return Karate.run("classpath:features")
            .systemProperty("server.url", "http://localhost:${localServerPort}")
    }
}
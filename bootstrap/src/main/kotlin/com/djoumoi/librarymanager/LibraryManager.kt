package com.djoumoi.librarymanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibraryManager

fun main(args: Array<String>) {
    runApplication<LibraryManager>(*args)
}

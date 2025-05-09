package com.djoumoi.librarymanager.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
class BookEntity(val name: String, val isbn: String,val libraries : MutableSet<String>) {
    lateinit var id:String
}
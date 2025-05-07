package com.djoumoi.librarymanager.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class LibraryEntity(val name:String, val address: AddressEntity?, val contact: ContactEntity?) {
    @Id
    lateinit var id:String
}
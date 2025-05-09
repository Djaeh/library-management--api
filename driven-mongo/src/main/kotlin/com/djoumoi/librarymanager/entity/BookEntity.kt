package com.djoumoi.librarymanager.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
class BookEntity(val displayName: String, val isbn: String, val libraries : MutableSet<String>, val title: String? = null, val authors: Collection<String> = mutableSetOf<String>(), val publisher: String? = null, val publishedDate: String? = null, val description: String? = null, val googlePreviewLink: String? = null) {
    lateinit var id:String
}
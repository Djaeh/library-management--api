package com.djoumoi.librarymanager.spi

import com.djoumoi.librarymanager.model.ExternalBookInfo

fun interface BookInfoFromExternalSource {
    fun get(isbn: String): ExternalBookInfo?
}
package com.djoumoi.librarymanager.api

import com.djoumoi.librarymanager.model.Book
import com.djoumoi.librarymanager.model.Page

fun interface GetBookApi {
    fun getBooksFromLibrary(libraryId:String):Page<Book>
}
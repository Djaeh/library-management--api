package com.djoumoi.librarymanager.api

import com.djoumoi.librarymanager.model.Book
import com.djoumoi.librarymanager.model.BookAddRequest

fun interface AddBookToLibraryApi{
    fun addBookToLibrary(libraryId: String, bookAddRequest: BookAddRequest) : Book
}

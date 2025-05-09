package com.djoumoi.librarymanager.api

import com.djoumoi.librarymanager.model.Book

interface AddBookToLibraryApi{
    fun addBookToLibrary(libraryId: String, book: Book) : Book
}

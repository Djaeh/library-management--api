package com.djoumoi.librarymanager.spi

import com.djoumoi.librarymanager.model.Book
import com.djoumoi.librarymanager.model.Page

interface BookOperationSpi {
    fun saveBookToLibrary(libraryId: String, book : Book) : Book
    fun getBooksFromLibrary(libraryId : String) : Page<Book>
}
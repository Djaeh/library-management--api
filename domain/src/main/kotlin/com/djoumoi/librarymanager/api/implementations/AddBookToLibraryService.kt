package com.djoumoi.librarymanager.api.implementations

import com.djoumoi.librarymanager.api.AddBookToLibraryApi
import com.djoumoi.librarymanager.model.Book
import com.djoumoi.librarymanager.spi.BookOperationSpi

class AddBookToLibraryService(val bookOperationSpi: BookOperationSpi): AddBookToLibraryApi {
    override fun addBookToLibrary(libraryId: String, book: Book): Book {
        //TODO enrich with external API openBookAPI or googleBook
        return bookOperationSpi.saveBookToLibrary(libraryId, book)
    }
}
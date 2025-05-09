package com.djoumoi.librarymanager.api.implementations

import com.djoumoi.librarymanager.api.AddBookToLibraryApi
import com.djoumoi.librarymanager.model.Book
import com.djoumoi.librarymanager.model.BookAddRequest
import com.djoumoi.librarymanager.spi.BookInfoFromExternalSource
import com.djoumoi.librarymanager.spi.BookOperationSpi

class AddBookToLibraryService(val bookOperationSpi: BookOperationSpi, val getInfoFromExternalSource: BookInfoFromExternalSource): AddBookToLibraryApi {
    override fun addBookToLibrary(libraryId: String, bookToAdd: BookAddRequest): Book {
        lateinit var book: Book
        val bookMetadata = getInfoFromExternalSource.get(bookToAdd.isbn)
        if (bookMetadata != null) {
            book = Book(bookToAdd.name, bookToAdd.isbn, bookMetadata.title, bookMetadata.authors, bookMetadata.publisher, bookMetadata.publishedDate, bookMetadata.description, bookMetadata.googlePreviewLink)
        } else {
            Book(bookToAdd.name, bookToAdd.isbn)
        }
        return bookOperationSpi.saveBookToLibrary(libraryId, book)
    }
}
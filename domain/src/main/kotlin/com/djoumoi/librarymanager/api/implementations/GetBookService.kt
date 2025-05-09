package com.djoumoi.librarymanager.api.implementations

import com.djoumoi.librarymanager.api.GetBookApi
import com.djoumoi.librarymanager.model.Book
import com.djoumoi.librarymanager.model.Page
import com.djoumoi.librarymanager.spi.BookOperationSpi

class GetBookService(val bookOperationSpi: BookOperationSpi): GetBookApi {
    override fun getBooksFromLibrary(libraryId: String): Page<Book> {
        return bookOperationSpi.getBooksFromLibrary(libraryId)
    }
}
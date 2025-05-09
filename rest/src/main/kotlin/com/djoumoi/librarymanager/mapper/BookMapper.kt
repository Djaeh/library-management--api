package com.djoumoi.librarymanager.mapper

import com.djoumoi.librarymanager.model.Book
import com.djoumoi.librarymanager.model.BookAddRequest

class BookMapper {

    companion object {
        fun toRestModel(book: Book) : com.djoumoi.librarymanagement.rest.model.Book {
            val bookRest = com.djoumoi.librarymanagement.rest.model.Book()
            bookRest.isbn = book.isbn
            bookRest.displayName = book.displayName
            bookRest.title = book.title
            bookRest.authors = book.authors.toList()
            bookRest.publisher = book.publisher
            bookRest.publishedDate = book.publishedDate
            bookRest.description = book.description
            bookRest.googlePreviewLink = book.googlePreviewLink
            return bookRest
        }

        fun toDomain(book: com.djoumoi.librarymanagement.rest.model.BookAddRequest): BookAddRequest {
            return BookAddRequest(book.displayName, book.isbn)
        }
    }
}
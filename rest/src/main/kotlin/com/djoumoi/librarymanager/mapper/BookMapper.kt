package com.djoumoi.librarymanager.mapper

import com.djoumoi.librarymanager.model.Book

class BookMapper {

    companion object {
        fun toRestModel(book: Book) : com.djoumoi.librarymanagement.rest.model.Book {
            val bookRest = com.djoumoi.librarymanagement.rest.model.Book()
            bookRest.isbn = book.isbn
            bookRest.title = book.name
            return bookRest
        }

        fun toDomain(book: com.djoumoi.librarymanagement.rest.model.Book): Book {
            return Book(book.title, book.isbn)
        }
    }
}
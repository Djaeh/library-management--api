package com.djoumoi.librarymanager.adapter

import com.djoumoi.librarymanager.entity.BookEntity
import com.djoumoi.librarymanager.model.Book
import com.djoumoi.librarymanager.model.Page
import com.djoumoi.librarymanager.repository.BookRepository
import com.djoumoi.librarymanager.spi.BookOperationSpi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookOperationAdapter : BookOperationSpi {

    @Autowired
    lateinit var bookRepository: BookRepository

    override fun saveBookToLibrary(
        libraryId: String,
        book: Book
    ): Book {
        val bookEntity = bookRepository.findByIsbn(book.isbn)?: BookEntity(name = book.name, isbn = book.isbn, mutableSetOf())
        bookEntity.libraries.add(libraryId)
        return toDomain(bookRepository.save(bookEntity))
    }

    private fun toDomain(bookEntity: BookEntity): Book {
        return Book(name = bookEntity.name, isbn = bookEntity.isbn)
    }

    // TODO: Implement pagination
    override fun getBooksFromLibrary(libraryId: String): Page<Book> {
        return Page(bookRepository.findByLibrariesContains(libraryId).map { toDomain(it) })
    }
}
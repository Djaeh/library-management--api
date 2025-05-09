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
        val bookEntity = bookRepository.findByIsbn(book.isbn)?: BookEntity(displayName = book.displayName,
            isbn = book.isbn,
            libraries = mutableSetOf(),
            title = book.title,
            authors = book.authors,
            publisher = book.publisher,
            publishedDate = book.publishedDate,
            description = book.description,
            googlePreviewLink = book.googlePreviewLink
        )
        bookEntity.libraries.add(libraryId)
        return toDomain(bookRepository.save(bookEntity))
    }

    private fun toDomain(bookEntity: BookEntity): Book {
        return Book(displayName = bookEntity.displayName,
            isbn = bookEntity.isbn,
            title = bookEntity.title,
            authors = bookEntity.authors,
            publisher = bookEntity.publisher,
            publishedDate = bookEntity.publishedDate,
            description = bookEntity.description,
            googlePreviewLink = bookEntity.googlePreviewLink)
    }

    // TODO: Implement pagination
    override fun getBooksFromLibrary(libraryId: String): Page<Book> {
        return Page(bookRepository.findByLibrariesContains(libraryId).map { toDomain(it) })
    }
}
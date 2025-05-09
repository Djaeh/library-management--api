package com.djoumoi.librarymanager

import com.djoumoi.librarymanagement.rest.LibrariesApi
import com.djoumoi.librarymanagement.rest.model.*
import com.djoumoi.librarymanager.api.AddBookToLibraryApi
import com.djoumoi.librarymanager.api.CreateLibraryApi
import com.djoumoi.librarymanager.api.GetBookApi
import com.djoumoi.librarymanager.api.GetLibraryApi
import com.djoumoi.librarymanager.mapper.BookMapper
import com.djoumoi.librarymanager.mapper.LibraryMapper.Companion.toDomainModel
import com.djoumoi.librarymanager.mapper.LibraryMapper.Companion.toRestModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

//TODO Global Exception handling with @ExceptionHandler and @ControllerAdvice
@RestController
class LibraryController : LibrariesApi {
    @Autowired
    lateinit var getLibraryApi: GetLibraryApi

    @Autowired
    lateinit var createLibrary: CreateLibraryApi

    @Autowired
    lateinit var addBookToLibraryApi: AddBookToLibraryApi

    @Autowired
    lateinit var getBookApi: GetBookApi

    override fun getLibraries(): ResponseEntity<GetLibraries200Response?>? {
        return ResponseEntity.ok(
            GetLibraries200Response(getLibraryApi.getAllLibrary().map {
                toRestModel(it)

            })
        )
    }

    override fun addLibrary(baseLibrary: @Valid BaseLibrary?): ResponseEntity<Library?>? {
        return ResponseEntity(
            toRestModel(
                createLibrary.createLibrary(
                    toDomainModel(baseLibrary)
                )
            ), HttpStatus.CREATED
        )
    }

    override fun addBookToLibrary(
        libraryId: String?,
        bookAddRequest: @Valid BookAddRequest?
    ): ResponseEntity<Book?>? {
        requireNotNull(libraryId)
        requireNotNull(bookAddRequest)
        require(bookAddRequest.isbn.isNotBlank())
        require(bookAddRequest.displayName.isNotBlank())
        return ResponseEntity(
            BookMapper.toRestModel(
                addBookToLibraryApi.addBookToLibrary(libraryId, BookMapper.toDomain(bookAddRequest))
            ), HttpStatus.CREATED
        )
    }

    override fun getBooksByLibrary(libraryId: String?): ResponseEntity<PaginatedListOfBooks?>? {
        requireNotNull(libraryId)
        require(libraryId.isNotBlank())
        return ResponseEntity.ok(
            PaginatedListOfBooks(
                getBookApi.getBooksFromLibrary(libraryId).datas.map { BookMapper.toRestModel(it) })
        )
    }
}
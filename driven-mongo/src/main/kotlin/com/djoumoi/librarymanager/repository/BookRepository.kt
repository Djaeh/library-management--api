package com.djoumoi.librarymanager.repository

import com.djoumoi.librarymanager.entity.BookEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : MongoRepository<BookEntity, String> {
    fun findByIsbn(isbn:String):BookEntity?
    fun findByLibrariesContains(libraryId:String):List<BookEntity>
}
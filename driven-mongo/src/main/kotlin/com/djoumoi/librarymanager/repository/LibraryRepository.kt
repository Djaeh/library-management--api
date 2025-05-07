package com.djoumoi.librarymanager.repository

import com.djoumoi.librarymanager.entity.LibraryEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LibraryRepository : MongoRepository<LibraryEntity, String> {
}
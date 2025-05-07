package com.djoumoi.librarymanager.spi

import com.djoumoi.librarymanager.model.Library

interface LibraryOperationSpi {
    fun getAll(): List<Library>
    fun save(library : Library) : Library
}
package com.djoumoi.librarymanager.api

import com.djoumoi.librarymanager.model.Library

interface GetLibraryApi {
    fun getAllLibrary(): List<Library>
}
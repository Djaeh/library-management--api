package com.djoumoi.librarymanager.api

import com.djoumoi.librarymanager.model.Library

fun interface GetLibraryApi {
    fun getAllLibrary(): List<Library>
}
package com.djoumoi.librarymanager.api

import com.djoumoi.librarymanager.model.Library

fun interface CreateLibraryApi {
    fun createLibrary(library : Library) : Library
}
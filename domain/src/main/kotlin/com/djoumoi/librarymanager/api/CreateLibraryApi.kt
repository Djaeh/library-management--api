package com.djoumoi.librarymanager.api

import com.djoumoi.librarymanager.model.Library

interface CreateLibraryApi {
    fun createLibrary(library : Library) : Library
}
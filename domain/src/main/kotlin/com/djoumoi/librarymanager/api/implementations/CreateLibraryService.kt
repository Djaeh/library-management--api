package com.djoumoi.librarymanager.api.implementations

import com.djoumoi.librarymanager.api.CreateLibraryApi
import com.djoumoi.librarymanager.model.Library
import com.djoumoi.librarymanager.spi.LibraryOperationSpi

class CreateLibraryService(val libraryOperationSpi: LibraryOperationSpi): CreateLibraryApi {
    override fun createLibrary(library: Library): Library {
        // TODO("Do business validation")
        return libraryOperationSpi.save(library)
    }
}
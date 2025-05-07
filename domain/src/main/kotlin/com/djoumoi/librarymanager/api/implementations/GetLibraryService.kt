package com.djoumoi.librarymanager.api.implementations

import com.djoumoi.librarymanager.api.GetLibraryApi
import com.djoumoi.librarymanager.model.Library
import com.djoumoi.librarymanager.spi.LibraryOperationSpi

class GetLibraryService(val libraryOperationSpi: LibraryOperationSpi): GetLibraryApi  {
    override fun getAllLibrary(): List<Library> {
        return libraryOperationSpi.getAll()
    }
}
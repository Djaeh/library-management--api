package com.djoumoi.librarymanager

import com.djoumoi.librarymanager.api.CreateLibraryApi
import com.djoumoi.librarymanager.api.GetLibraryApi
import com.djoumoi.librarymanager.api.implementations.CreateLibraryService
import com.djoumoi.librarymanager.api.implementations.GetLibraryService
import com.djoumoi.librarymanager.spi.LibraryOperationSpi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {


    @Bean
    fun createLibraryApi(libraryOperationSpi: LibraryOperationSpi): CreateLibraryApi {
        return CreateLibraryService(libraryOperationSpi)
    }

    @Bean
    fun getLibraryApi(librarySpi: LibraryOperationSpi) : GetLibraryApi {
        return GetLibraryService(librarySpi)
    }

}
package com.djoumoi.librarymanager

import com.djoumoi.librarymanagement.rest.LibrariesApi
import com.djoumoi.librarymanagement.rest.model.BaseLibrary
import com.djoumoi.librarymanagement.rest.model.GetLibraries200Response
import com.djoumoi.librarymanagement.rest.model.Library
import com.djoumoi.librarymanager.api.CreateLibraryApi
import com.djoumoi.librarymanager.api.GetLibraryApi
import com.djoumoi.librarymanager.model.Address
import com.djoumoi.librarymanager.model.Contact
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class LibraryController : LibrariesApi {
    @Autowired
    lateinit var getLibraryApi: GetLibraryApi
    @Autowired
    lateinit var createLibrary: CreateLibraryApi

    override fun getLibraries(): ResponseEntity<GetLibraries200Response?>? {
        return ResponseEntity.ok(
            GetLibraries200Response( getLibraryApi.getAllLibrary().map {
                toRestModel(it)

            })
        )
    }

    override fun addLibrary(baseLibrary: @Valid BaseLibrary?): ResponseEntity<Library?>? {
        return ResponseEntity(
            toRestModel(
                createLibrary.createLibrary(
                    toDomainModel(baseLibrary)
                )
            ), HttpStatus.CREATED
        )
    }

    private fun toDomainModel(baseLibrary: @Valid BaseLibrary?): com.djoumoi.librarymanager.model.Library =
        if (baseLibrary == null) {
            throw IllegalArgumentException()
        } else
        com.djoumoi.librarymanager.model.Library(
            null, baseLibrary.name,
            Address(
                baseLibrary.address?.addressLine1,
                baseLibrary.address?.addressLine2,
                baseLibrary.address?.addressLine3,
                baseLibrary.address?.zipCode
            ),
            Contact(
                baseLibrary.contact?.firstname,
                baseLibrary.contact?.lastname
            )
        )

    fun toRestModel(library: com.djoumoi.librarymanager.model.Library): Library {
        val restLibrary = Library(library.name)
        restLibrary.id(library.id)
        val address = com.djoumoi.librarymanagement.rest.model.Address()
        address.addressLine1 = library.address?.addressLine1
        address.addressLine2 = library.address?.addressLine2
        address.addressLine3 = library.address?.addressLine3
        address.zipCode = library.address?.zipCode
        restLibrary.address = address
        val contact = com.djoumoi.librarymanagement.rest.model.Contact()
        contact.lastname = library.contact?.lastname
        contact.firstname = library.contact?.firstname
        restLibrary.contact = contact
        return restLibrary
    }
}
package com.djoumoi.librarymanager.mapper

import com.djoumoi.librarymanagement.rest.model.BaseLibrary
import com.djoumoi.librarymanagement.rest.model.Library
import com.djoumoi.librarymanager.model.Address
import com.djoumoi.librarymanager.model.Contact
import jakarta.validation.Valid

class LibraryMapper {
    companion object {
        fun toDomainModel(baseLibrary: @Valid BaseLibrary?): com.djoumoi.librarymanager.model.Library {
            requireNotNull(baseLibrary)
            return com.djoumoi.librarymanager.model.Library(
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
        }

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
}
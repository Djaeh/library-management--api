package com.djoumoi.librarymanager.adapter

import com.djoumoi.librarymanager.entity.AddressEntity
import com.djoumoi.librarymanager.entity.ContactEntity
import com.djoumoi.librarymanager.entity.LibraryEntity
import com.djoumoi.librarymanager.model.Address
import com.djoumoi.librarymanager.model.Contact
import com.djoumoi.librarymanager.model.Library
import com.djoumoi.librarymanager.repository.LibraryRepository
import com.djoumoi.librarymanager.spi.LibraryOperationSpi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
 class LibraryOperationAdapter: LibraryOperationSpi {
     @Autowired
    lateinit var libraryRepository: LibraryRepository
    override fun getAll(): List<Library> {
        return libraryRepository.findAll().map { toLibraryDomain(it) }
    }

    private fun toLibraryDomain(entity: LibraryEntity): Library = Library(
        id = entity.id, name = entity.name,
        address = Address(
            entity.address?.addressLine1,
            entity.address?.addressLine2,
            entity.address?.addressLine3,
            entity.address?.zipCode
        ),
        contact = Contact(entity.contact?.firstname, entity.contact?.lastname)
    )

    override fun save(library: Library): Library {
        return toLibraryDomain(
            libraryRepository.save(toLibraryEntity(library))
        )
    }

    private fun toLibraryEntity(library: Library): LibraryEntity = LibraryEntity(
        name = library.name,
        address = AddressEntity(
            library.address?.addressLine1,
            library.address?.addressLine2,
            library.address?.addressLine3,
            library.address?.zipCode
        ),
        contact = ContactEntity(library.contact?.firstname, library.contact?.lastname)
    )
}
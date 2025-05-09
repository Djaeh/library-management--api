package com.djoumoi.librarymanager

import com.djoumoi.librarymanager.model.ExternalBookInfo
import com.djoumoi.librarymanager.spi.BookInfoFromExternalSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class BookFromExternalSourceAdapter : BookInfoFromExternalSource {

    @Value("\${google.book.url}")
    lateinit var googleBookURI: String
    override fun get(isbn: String): ExternalBookInfo? {
        val googleResponse =
            RestTemplate().getForObject<GoogleResponse>("$googleBookURI?q=isbn:$isbn", GoogleResponse::class.java)
        return toDomain(googleResponse?.items?.first())
    }

    private fun toDomain(book: GoogleBook?): ExternalBookInfo? {
        return ExternalBookInfo(
            book?.volumeInfo?.title ?: "",
            book?.volumeInfo?.authors ?: mutableSetOf(),
            book?.volumeInfo?.publisher ?: "",
            book?.volumeInfo?.publishedDate ?: "",
            book?.volumeInfo?.description ?: "",
            book?.volumeInfo?.previewLink
        )
    }

}
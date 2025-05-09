package com.djoumoi.librarymanager.model

class Book(val displayName: String, val isbn: String, val title: String? = null, val authors: Collection<String> = mutableSetOf<String>(), val publisher: String? = null, val publishedDate: String? = null, val description: String? = null, val googlePreviewLink: String? = null) {
}

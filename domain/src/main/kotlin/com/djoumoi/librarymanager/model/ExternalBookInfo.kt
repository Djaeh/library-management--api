package com.djoumoi.librarymanager.model

data class ExternalBookInfo(val title: String, val authors: Collection<String>, val publisher: String, val publishedDate: String, val description: String, val googlePreviewLink: String?) {
}

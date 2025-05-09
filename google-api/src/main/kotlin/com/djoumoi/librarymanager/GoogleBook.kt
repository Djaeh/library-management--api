package com.djoumoi.librarymanager

data class GoogleResponse(val totalItems: Int, val items: List<GoogleBook>) { }
data class GoogleBook(val volumeInfo : VolumeInfo) {
}

data class VolumeInfo(val title: String, val authors: List<String>, val publisher: String, val publishedDate: String,val description: String, val previewLink: String) {
}

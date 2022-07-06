package tech.ericwathome.momentskt.model

data class ImageResponseMessage(
    val name: String?,
    val downloadUrl: String?,
    val contentType: String?,
    val size: Long
)
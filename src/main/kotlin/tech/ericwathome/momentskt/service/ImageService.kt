package tech.ericwathome.momentskt.service

import org.springframework.web.multipart.MultipartFile
import tech.ericwathome.momentskt.model.Image

interface ImageService {
    fun uploadImage(image: MultipartFile): Image
    fun allImages(): List<Image>
    fun getImage(imageId: String): Image
}
package tech.ericwathome.momentskt.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import tech.ericwathome.momentskt.model.Image
import tech.ericwathome.momentskt.repository.ImageRepository

@Service
class ImageServiceImpl(
    @Autowired val imageRepository: ImageRepository
) : ImageService {
    override fun uploadImage(image: MultipartFile): Image {
        val fileName = StringUtils.cleanPath(image.originalFilename!!)
        try {
            if (fileName.contains("..")) {
                throw Exception("filename contains invalid path sequence")
            }
            val newImage = Image(name = fileName, type = image.contentType!!, data = image.bytes)
            return imageRepository.save(newImage)
        } catch (e: java.lang.Exception) {
            throw Exception("could not save file $fileName")
        }
    }

    override fun allImages(): List<Image> {
        return imageRepository.findAll()
    }

    override fun getImage(imageId: String): Image {
        return imageRepository.findById(imageId)
            .orElseThrow { Exception("file not found with id $imageId") }
    }
}
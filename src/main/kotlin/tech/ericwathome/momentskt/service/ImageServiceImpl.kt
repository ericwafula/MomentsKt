package tech.ericwathome.momentskt.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.ericwathome.momentskt.model.Image
import tech.ericwathome.momentskt.repository.ImageRepository

@Service
class ImageServiceImpl(
    @Autowired val imageRepository: ImageRepository
) : ImageService {
    override fun addImage(image: Image) {
        if (image.name.isNotBlank() && image.imgUrl.isNotBlank()) {
            imageRepository.save(image)
        }
    }

    override fun allImages(): List<Image> {
        return imageRepository.findAll()
    }
}
package tech.ericwathome.momentskt.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import tech.ericwathome.momentskt.model.Image
import tech.ericwathome.momentskt.model.ImageResponseMessage
import tech.ericwathome.momentskt.service.ImageService

@RestController
@RequestMapping("api/v1/images")
class ImageController(
    @Autowired val imageService: ImageService
) {
    @PostMapping("upload")
    fun uploadImage(@RequestParam("image") image: MultipartFile): ResponseEntity<ImageResponseMessage> {
        val imageAttachment = imageService.uploadImage(image)
        val downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/download/")
            .path(imageAttachment.id!!)
            .toUriString()
        val message = ImageResponseMessage(image.name, downloadUrl, image.contentType, image.size)
        return ResponseEntity(message, HttpStatus.OK)
    }

    @GetMapping
    fun allImages(): ResponseEntity<List<Image>> {
        return ResponseEntity(imageService.allImages(), HttpStatus.OK)
    }

    @GetMapping("/download/{imageId}")
    fun downloadImage(@PathVariable("imageId") imageId: String): ResponseEntity<Resource> {
        val image: Image = imageService.getImage(imageId)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(image.type))
            .header(HttpHeaders.CONTENT_DISPOSITION, "image; filename=\"${image.name}\"")
            .body(ByteArrayResource(image.data))
    }

}
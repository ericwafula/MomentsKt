package tech.ericwathome.momentskt.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.ericwathome.momentskt.model.Image
import tech.ericwathome.momentskt.model.ResponseMessage
import tech.ericwathome.momentskt.service.ImageService

@RestController
@RequestMapping("api/v1/images")
class ImageController(
    @Autowired val imageService: ImageService
) {
    @PostMapping("new")
    fun addImage(@RequestBody image: Image): ResponseEntity<ResponseMessage> {
        val message = ResponseMessage("image added successfully")
        imageService.addImage(image)
        return ResponseEntity(message, HttpStatus.OK)
    }

    @GetMapping
    fun allImages(): ResponseEntity<List<Image>> {
        return ResponseEntity(imageService.allImages(), HttpStatus.OK)
    }

}
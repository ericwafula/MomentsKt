package tech.ericwathome.momentskt.service

import tech.ericwathome.momentskt.model.Image

interface ImageService {
    fun addImage(image: Image)
    fun allImages(): List<Image>
}
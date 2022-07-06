package tech.ericwathome.momentskt.repository

import org.springframework.data.jpa.repository.JpaRepository
import tech.ericwathome.momentskt.model.Image

interface ImageRepository : JpaRepository<Image, String> {
}
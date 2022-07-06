package tech.ericwathome.momentskt.model

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class Image(
    @Id
    @SequenceGenerator(
        name = "image_sequence",
        sequenceName = "image_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "image_sequence"
    )
    val id: Long,
    val name: String,
    @Lob val imgUrl: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Image

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , imgUrl = $imgUrl )"
    }
}
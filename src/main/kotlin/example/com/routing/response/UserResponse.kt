package example.com.routing.response

import example.com.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserResponse(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val username: String,
)

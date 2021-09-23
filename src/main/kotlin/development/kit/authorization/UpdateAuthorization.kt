package development.kit.authorization
import java.time.OffsetDateTime

data class UpdateAuthorization(
    val start: OffsetDateTime,
    val end: OffsetDateTime,
    val reason: String
)
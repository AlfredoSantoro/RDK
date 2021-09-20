package development.kit.authorization

import development.kit.user.User
import java.time.OffsetDateTime

data class Authorization(
    var start: OffsetDateTime,
    var end: OffsetDateTime,
    var user: User,
    val id: Long
)
{
    var reason: String ? = null
    var granted: Boolean = false
}

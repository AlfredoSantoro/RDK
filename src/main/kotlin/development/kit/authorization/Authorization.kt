package development.kit.authorization

import development.kit.user.Account
import java.time.OffsetDateTime

data class Authorization(
    var start: OffsetDateTime,
    var end: OffsetDateTime,
    var account: Account,
    val id: Long
)
{
    var reason: String ? = null
    var granted: Boolean = false
}

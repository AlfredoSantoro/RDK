package development.kit.authorization

import development.kit.exception.IllegalAuthorizationException
import development.kit.time.DateTimeManager
import development.kit.user.Account
import java.time.OffsetDateTime

class Authorization(
    var start: OffsetDateTime,
    var end: OffsetDateTime,
    var account: Account
)
{
    val uniqueId: Long ? = null
    var reason: String ? = null
    var granted: Boolean = false

    init
    {
        if ( !DateTimeManager.isStartDateTimeBeforeEndDateTime(this.start, this.end) )
        {
            throw IllegalAuthorizationException("Illegal Authorization: start >= end")
        }
    }
}

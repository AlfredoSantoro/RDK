package development.kit.authorization

import java.time.OffsetDateTime

interface IAuthorization
{
    fun findUserAuthorizationOverlaps(start: OffsetDateTime, end: OffsetDateTime, userID: Long): Int
}
package development.kit.authorization

import java.time.OffsetDateTime

interface IAuthorizationStorage
{
    fun findAuthorizationBetween(start: OffsetDateTime, end: OffsetDateTime, username: String): Authorization?
    fun findAuthorizationOverlaps(start: OffsetDateTime, end: OffsetDateTime, userID: Long): Int
}
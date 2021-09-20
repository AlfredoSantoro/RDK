package development.kit.authorization

import java.time.OffsetDateTime

class AuthorizationManager(
    private val iAuthorization: IAuthorization
)
{
    fun userHaveAuthorization(start: OffsetDateTime, end: OffsetDateTime, username: String): Boolean
    {
        val userAuthorization = this.iAuthorization.findAuthorizationBetween(start, end, username)
        if ( userAuthorization === null ) return false
        return userAuthorization.granted
    }

    fun areAuthorizationsOverlaps(start: OffsetDateTime, end: OffsetDateTime, userId: Long): Boolean
    {
        return this.iAuthorization.findAuthorizationOverlaps(start, end, userId) > 0
    }

    fun approveAuthorization(authorization: Authorization): Authorization
    {
        authorization.granted = true
        return authorization
    }
}
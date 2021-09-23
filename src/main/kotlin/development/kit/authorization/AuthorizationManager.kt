package development.kit.authorization

import development.kit.user.Account
import java.time.OffsetDateTime

class AuthorizationManager(
    private val iAuthorizationStorage: IAuthorizationStorage
)
{
    fun createAuthorization(start: OffsetDateTime, end: OffsetDateTime, account: Account): Authorization
    {
        return Authorization(start, end, account)
    }

    fun updateAuthorization(updateAuthorization: UpdateAuthorization, newAuthorization: Authorization): Authorization
    {
        newAuthorization.start = updateAuthorization.start
        newAuthorization.end = updateAuthorization.end
        newAuthorization.reason = updateAuthorization.reason
        return newAuthorization
    }

    fun userHaveAuthorization(start: OffsetDateTime, end: OffsetDateTime, username: String): Boolean
    {
        val userAuthorization = this.iAuthorizationStorage.findAuthorizationBetween(start, end, username)
        if ( userAuthorization === null ) return false
        return userAuthorization.granted
    }

    fun areAuthorizationsOverlaps(start: OffsetDateTime, end: OffsetDateTime, userId: Long): Boolean
    {
        return this.iAuthorizationStorage.findAuthorizationOverlaps(start, end, userId) > 0
    }

    fun approveAuthorization(authorization: Authorization): Authorization
    {
        authorization.granted = true
        return authorization
    }

    fun rejectAuthorization(authorization: Authorization): Authorization
    {
        authorization.granted = false
        return authorization
    }
}
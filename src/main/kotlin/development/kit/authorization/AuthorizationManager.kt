package development.kit.authorization

import development.kit.user.Account
import java.time.OffsetDateTime

object AuthorizationManager
{
    fun createAuthorization(start: OffsetDateTime, end: OffsetDateTime, account: Account): Authorization
    {
        return Authorization(start, end, account)
    }

    fun updateAuthorization(newData: UpdateAuthorization, authorizationToUpdate: Authorization): Authorization
    {
        authorizationToUpdate.start = newData.start
        authorizationToUpdate.end = newData.end
        authorizationToUpdate.reason = newData.reason
        return authorizationToUpdate
    }

    fun userHaveAuthorization(userAuthorization: Authorization?): Boolean
    {
        return userAuthorization?.granted ?: false
    }

    fun areAuthorizationsOverlaps(iAuthorizationStorage: IAuthorization,
                                  start: OffsetDateTime,
                                  end: OffsetDateTime,
                                  userId: Long): Boolean
    {
        return iAuthorizationStorage.findUserAuthorizationOverlaps(start, end, userId) > 0
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
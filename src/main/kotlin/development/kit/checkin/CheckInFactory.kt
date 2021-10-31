package development.kit.checkin

import development.kit.reservation.BaseReservation
import development.kit.user.Account
import java.time.OffsetDateTime

object CheckInFactory
{
    fun createDefaultCheckInNow(baseReservation: BaseReservation, owner: Account, checkInId: Long): CheckIn
    {
        return CheckIn(baseReservation, OffsetDateTime.now(), owner, checkInId)
    }
}
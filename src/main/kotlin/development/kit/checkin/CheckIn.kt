package development.kit.checkin

import development.kit.reservation.BaseReservation
import development.kit.user.Account
import java.time.OffsetDateTime

class CheckIn(
    val reservation: BaseReservation,
    val time: OffsetDateTime,
    val owner: Account,
    val checkInId: Long
)
{
    var isValid: Boolean = true
}

package development.kit.checkin

import development.kit.reservation.BaseReservation
import development.kit.user.Account
import java.time.OffsetDateTime

data class CheckIn(
    val reservation: BaseReservation,
    val time: OffsetDateTime,
    val owner: Account
)
{
    val uniqueId: Long ? = null
    var isValid: Boolean = true
}

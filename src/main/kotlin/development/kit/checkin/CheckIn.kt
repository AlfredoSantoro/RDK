package development.kit.checkin

import development.kit.reservation.BaseReservation
import java.time.OffsetDateTime

data class CheckIn(
    val reservation: BaseReservation,
    val time: OffsetDateTime,
    val id: Long
)
{
    var isValid: Boolean = true
}

package development.kit.reservation

import development.kit.asset.Seat
import development.kit.user.User
import java.time.OffsetDateTime

data class SeatsReservation(
    var name: String,
    var startBooking: OffsetDateTime,
    var endBooking: OffsetDateTime,
    var seat: Seat,
    val user: User,
    val id: Long
): BaseReservation(startBooking, endBooking, seat, user, id)
{
    var inPause: Boolean = false
}
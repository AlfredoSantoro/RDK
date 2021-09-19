package development.kit.reservation

import development.kit.asset.Seat
import development.kit.user.User
import java.time.OffsetDateTime

data class SeatsBooking(
    var name: String,
    var startBooking: OffsetDateTime,
    var endBooking: OffsetDateTime,
    var seat: Seat,
    var user: User
): BaseReservation(startBooking, endBooking, seat, user)
{
    var inPause: Boolean = false
}
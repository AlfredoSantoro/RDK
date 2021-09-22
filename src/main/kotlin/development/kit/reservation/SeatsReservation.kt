package development.kit.reservation

import development.kit.asset.Seat
import development.kit.user.Account
import java.time.OffsetDateTime

data class SeatsReservation(
    var name: String,
    var startBooking: OffsetDateTime,
    var endBooking: OffsetDateTime,
    var seat: Seat,
    val account: Account,
    val id: Long
): BaseReservation(startBooking, endBooking, seat, account, id)
{
    var inPause: Boolean = false
}
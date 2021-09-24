package development.kit.reservation

import development.kit.asset.Seat
import development.kit.user.Account
import java.time.OffsetDateTime

data class SeatsReservation(
    var name: String,
    var reservationStart: OffsetDateTime,
    var reservationEnd: OffsetDateTime,
    var seat: Seat,
    val account: Account,
): BaseReservation(reservationStart, reservationEnd, seat, account)
{
    init
    {

    }
    var inPause: Boolean = false
}
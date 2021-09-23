package development.kit.reservation

import development.kit.asset.Seat
import development.kit.user.Account
import java.time.OffsetDateTime

data class SeatsReservation(
    var name: String,
    override var start: OffsetDateTime,
    override var end: OffsetDateTime,
    var seat: Seat,
    val account: Account,
): BaseReservation(start, end, seat, account)
{
    var inPause: Boolean = false
}
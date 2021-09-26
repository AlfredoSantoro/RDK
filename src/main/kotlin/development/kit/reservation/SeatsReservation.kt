package development.kit.reservation

import development.kit.asset.Seat
import development.kit.user.Account
import java.time.OffsetDateTime

class SeatsReservation(
    var name: String,
    start: OffsetDateTime,
    end: OffsetDateTime,
    var seat: Seat,
    owner: Account,
): BaseReservation(start, end, seat, owner)
{
    var inPause: Boolean = false
}
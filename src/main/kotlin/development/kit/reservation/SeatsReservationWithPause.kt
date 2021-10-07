package development.kit.reservation

import development.kit.asset.Seat
import development.kit.user.Account
import java.time.OffsetDateTime

open class SeatsReservationWithPause(
    var name: String,
    start: OffsetDateTime,
    end: OffsetDateTime,
    var seat: Seat,
    owner: Account,
    var inPause: Boolean = false
): BaseReservation(start, end, seat, owner)
{
    constructor(): this(
        "",
        OffsetDateTime.now(),
        OffsetDateTime.now(),
        Seat("", true),
        Account()
    )
}
package development.kit.reservation

import development.kit.asset.Asset
import development.kit.asset.Seat
import development.kit.user.Account
import java.time.OffsetDateTime

open class ReservationPause(
    start: OffsetDateTime,
    end: OffsetDateTime,
    asset: Asset,
    owner: Account,
    var inPause: Boolean = false
): BaseReservation(start, end, asset, owner)
{
    constructor(): this(
        OffsetDateTime.now(),
        OffsetDateTime.now(),
        Seat("", true),
        Account(),
    )
}
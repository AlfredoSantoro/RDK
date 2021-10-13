package development.kit.reservation

import development.kit.asset.Asset
import development.kit.user.Account
import java.time.OffsetDateTime

open class BaseReservation(
    var start: OffsetDateTime,
    var end: OffsetDateTime,
    var asset: Asset,
    val owner: Account
)
{
    init
    {
        ReservationValidation.validReservation(BaseReservation(this.start, this.end, this.asset, this.owner))
    }

    var reservationId: Long ? = null
}
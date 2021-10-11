package development.kit.reservation

import development.kit.asset.Asset
import development.kit.exception.IllegalReservationException
import development.kit.exception.ReservationConstraintsException
import development.kit.time.DateTimeManager
import development.kit.user.Account
import java.time.OffsetDateTime

open class BaseReservation(
    var start: OffsetDateTime,
    var end: OffsetDateTime,
    var asset: Asset,
    val owner: Account
)
{
    var id: Long = - 1

    init
    {
        if ( !DateTimeManager.isStartDateTimeBeforeEndDateTime(this.start, this.end) )
        {
            throw IllegalReservationException("Illegal Reservation: start >= end")
        }
        if ( !this.asset.canBeBooked ) throw ReservationConstraintsException("Asset ${this.asset.name} cannot be reserved")
    }
}
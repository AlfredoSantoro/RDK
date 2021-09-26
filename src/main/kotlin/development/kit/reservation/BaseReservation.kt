package development.kit.reservation

import development.kit.asset.Asset
import development.kit.exception.IllegalReservationException
import development.kit.exception.ReservationConstraintsException
import development.kit.time.DateTimeManager
import development.kit.user.User
import java.time.OffsetDateTime

abstract class BaseReservation(
    var start: OffsetDateTime,
    var end: OffsetDateTime,
    var asset: Asset,
    val owner: User
)
{
    val uniqueId: Long ? = null

    init
    {
        if ( !DateTimeManager.isStartDateTimeBeforeEndDateTime(this.start, this.end) )
        {
            throw IllegalReservationException("Illegal Reservation: start >= end")
        }
        if ( !this.asset.canBeBooked ) throw ReservationConstraintsException("Asset ${this.asset.name} cannot be reserved")
    }

    protected fun isOnGoing(start: OffsetDateTime, end: OffsetDateTime): Boolean
    {
        val now = OffsetDateTime.now()
        return (now >= start && now < end)
    }
}
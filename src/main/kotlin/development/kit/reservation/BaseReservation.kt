package development.kit.reservation

import development.kit.asset.Asset
import development.kit.exception.IllegalReservationException
import development.kit.exception.ReservationConstraintsException
import development.kit.time.DateTimeManager
import development.kit.user.User
import java.time.OffsetDateTime

open class BaseReservation(
    val start: OffsetDateTime,
    val end: OffsetDateTime,
    val asset: Asset,
    val owner: User
)
{
    open val uniqueId: Long ? = null

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
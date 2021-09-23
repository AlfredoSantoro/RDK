package development.kit.reservation

import development.kit.asset.Asset
import development.kit.exception.IllegalReservationException
import development.kit.time.DateTimeManager
import development.kit.user.User
import java.time.OffsetDateTime

open class BaseReservation(
    open var start: OffsetDateTime,
    open var end: OffsetDateTime,
    open var asset: Asset,
    open val owner: User
)
{
    open val uniqueId: Long ? = null

    init
    {
        if ( !DateTimeManager.isAValidPeriod(this.start, this.end) ) throw IllegalReservationException("Illegal Reservation: start > end or startTime = endTime")
    }

    protected fun isOnGoing(start: OffsetDateTime, end: OffsetDateTime): Boolean
    {
        val now = OffsetDateTime.now()
        return (now >= start && now < end)
    }
}
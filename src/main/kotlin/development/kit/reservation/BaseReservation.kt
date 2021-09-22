package development.kit.reservation

import development.kit.asset.Asset
import development.kit.exception.IllegalReservationException
import development.kit.time.DateTimeManager
import development.kit.user.User
import java.time.OffsetDateTime

open class BaseReservation(
    private var start: OffsetDateTime,
    private var end: OffsetDateTime,
    private var asset: Asset,
    private val owner: User,
    private val id: Long
)
{
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
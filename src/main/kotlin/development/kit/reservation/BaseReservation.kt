package development.kit.reservation

import development.kit.Person
import development.kit.asset.Asset
import development.kit.exception.IllegalReservationException
import development.kit.time.DateTimeManager
import java.time.Duration
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

abstract class BaseReservation(
    private var start: OffsetDateTime,
    private var end: OffsetDateTime,
    private var asset: Asset,
    private var owner: Person
)
{
    init
    {
        if ( !DateTimeManager.isAValidPeriod(this.start, this.end) ) throw IllegalReservationException("Illegal Reservation: start > end or startTime = endTime")
   }

    protected fun computeEndReservation(amountToBeAddedToStart: Duration)
    {
        val tempStart = OffsetDateTime.from(this.start)
        val tempEnd = tempStart.plus(amountToBeAddedToStart)
        if ( !DateTimeManager.isAValidPeriod(tempStart, tempEnd) )
        {
            throw IllegalReservationException("Illegal Reservation: start > end or startTime = endTime")
        }
        this.end = this.start.plus(amountToBeAddedToStart)
    }

    protected fun computeEndReservation(amountToBeAddedToStart: Long, representationUnit: ChronoUnit)
    {
        val tempStart = OffsetDateTime.from(this.start)
        val tempEnd = tempStart.plus(amountToBeAddedToStart, representationUnit)
        if ( !DateTimeManager.isAValidPeriod(tempStart, tempEnd) )
        {
            throw IllegalReservationException("Illegal Reservation: start > end or startTime = endTime")
        }
        this.end = this.start.plus(amountToBeAddedToStart, representationUnit)
    }

    protected fun isOnGoing(start: OffsetDateTime, end: OffsetDateTime): Boolean
    {
        val now = OffsetDateTime.now()
        return (now >= start && now < end)
    }
}
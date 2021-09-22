package development.kit.openingtimes

import java.time.OffsetDateTime
import java.time.OffsetTime

class OpeningTimeManager(
    private val iOpeningTime: IOpeningTimeStorage
)
{
    fun reservationIsIncludedInTheOpeningTimes(reservationStart: OffsetDateTime, reservationEnd: OffsetDateTime): Boolean
    {
        val openTime = this.iOpeningTime.findByDayOfWeek(reservationStart.dayOfWeek)
        return if ( openTime === null ) false
        else
        {
            reservationStart.toOffsetTime() >= openTime.open
                    && reservationStart.toOffsetTime() <= openTime.close
                    && reservationEnd.toOffsetTime() <= openTime.close
        }
    }

    fun updateOpeningTimes(openingTime: OpeningTime, newOpen: OffsetTime, newClose: OffsetTime): OpeningTime
    {
        openingTime.open = newOpen
        openingTime.close = newClose
        return openingTime
    }
}
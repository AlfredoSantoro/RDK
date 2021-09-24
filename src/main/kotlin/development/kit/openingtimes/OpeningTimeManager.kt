package development.kit.openingtimes

import java.time.DayOfWeek
import java.time.OffsetDateTime
import java.time.OffsetTime

object OpeningTimeManager
{
    fun reservationIsIncludedInTheOpeningTimes(periodicOpeningTime: PeriodicOpeningTime,
                                               reservationStart: OffsetDateTime,
                                               reservationEnd: OffsetDateTime): Boolean
    {
            return reservationStart.toOffsetTime() >= periodicOpeningTime.openingTime
                    && reservationStart.toOffsetTime() <= periodicOpeningTime.closingTime
                    && reservationEnd.toOffsetTime() <= periodicOpeningTime.closingTime
    }

    fun updatePeriodicOpeningTimes(openingTime: PeriodicOpeningTime,
                           newOpen: OffsetTime,
                           newClose: OffsetTime): PeriodicOpeningTime
    {
        openingTime.openingTime = newOpen
        openingTime.closingTime = newClose
        return openingTime
    }

    fun createPeriodicOpeningTime(dayOfWeek: DayOfWeek, open: OffsetTime, close: OffsetTime): PeriodicOpeningTime
    {
        return PeriodicOpeningTime(dayOfWeek, open, close)
    }
}
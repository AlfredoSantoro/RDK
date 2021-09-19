package development.kit.time

import java.time.OffsetDateTime
import java.time.OffsetTime

object DateTimeManager
{
    fun isAValidPeriod(start: OffsetDateTime, end: OffsetDateTime): Boolean
    {
        return if ( start.dayOfWeek == end.dayOfWeek )
        {
            this.isAValidTime(start.toOffsetTime(),end.toOffsetTime())
        }
        else
        {
            start.isBefore(end)
        }
    }

    fun isAValidTime(start: OffsetTime, end: OffsetTime): Boolean
    {
        return start < end
    }
}
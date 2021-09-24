package development.kit.time

import java.time.OffsetDateTime
import java.time.OffsetTime

object DateTimeManager
{

    fun isStartDateTimeBeforeEndDateTime(start: OffsetDateTime, end: OffsetDateTime): Boolean
    {
        return start.isBefore(end)
    }

    fun isStartTimeBeforeEndTime(start: OffsetTime, end: OffsetTime): Boolean
    {
        return start.isBefore(end)
    }
}
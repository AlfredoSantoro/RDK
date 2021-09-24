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

    fun isStartTimeAfterOrEqualEndTime(start: OffsetTime, end: OffsetTime): Boolean
    {
        return start.isAfter(end) || start == end
    }

    fun isStartDateTimeAfterOrEqualEndDateTime(start: OffsetDateTime, end: OffsetDateTime): Boolean
    {
        return start.isAfter(end) || start == end
    }


    fun isStartDateTimeBeforeOrEqualEndDateTime(start: OffsetDateTime, end: OffsetDateTime): Boolean
    {
        return start.isBefore(end) || start == end
    }
}
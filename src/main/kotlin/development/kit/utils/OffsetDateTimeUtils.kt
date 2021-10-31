package development.kit.utils

import java.time.Duration
import java.time.OffsetDateTime

object OffsetDateTimeUtils
{
    fun addDurationToTime(time: OffsetDateTime, duration: Duration): OffsetDateTime? { return time.plus(duration) }

    fun isStartGreaterThanEnd(start: OffsetDateTime, end: OffsetDateTime): Boolean { return start > end }
}
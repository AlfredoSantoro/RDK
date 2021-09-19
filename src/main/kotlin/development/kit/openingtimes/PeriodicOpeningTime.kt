package development.kit.openingtimes

import java.time.DayOfWeek
import java.time.OffsetTime

data class PeriodicOpeningTime(
    val dayOfWeek: DayOfWeek,
    val openTime: OffsetTime,
    val closeTime: OffsetTime
): OpeningTime(openTime, closeTime)

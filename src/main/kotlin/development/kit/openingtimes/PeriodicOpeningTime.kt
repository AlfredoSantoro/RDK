package development.kit.openingtimes

import java.time.DayOfWeek
import java.time.OffsetTime

data class PeriodicOpeningTime(
    val dayOfWeek: DayOfWeek,
    var openingTime: OffsetTime,
    var closingTime: OffsetTime
): OpeningTime(openingTime, closingTime)

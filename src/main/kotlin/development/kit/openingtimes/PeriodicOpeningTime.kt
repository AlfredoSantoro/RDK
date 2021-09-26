package development.kit.openingtimes

import java.time.DayOfWeek
import java.time.OffsetTime

class PeriodicOpeningTime(
    val dayOfWeek: DayOfWeek,
    open: OffsetTime,
    close: OffsetTime
): OpeningTime(open, close)

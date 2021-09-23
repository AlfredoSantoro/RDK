package development.kit.openingtimes

import java.time.DayOfWeek
import java.time.OffsetTime

data class PeriodicOpeningTime(
    val dayOfWeek: DayOfWeek,
    override var open: OffsetTime,
    override var close: OffsetTime
): OpeningTime(open, close)

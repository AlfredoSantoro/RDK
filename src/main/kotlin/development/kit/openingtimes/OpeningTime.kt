package development.kit.openingtimes

import development.kit.exception.OpeningTimeException
import development.kit.time.DateTimeManager
import java.time.OffsetTime

abstract class OpeningTime(
    var open: OffsetTime,
    var close: OffsetTime
)
{
    val uniqueId: Long ? = null

    init
    {
        if (DateTimeManager.isStartTimeAfterOrEqualEndTime(this.open, this.close)) throw OpeningTimeException("Illegal opening time: open >= close")
    }
}

package development.kit.openingtimes

import development.kit.exception.OpeningTimeException
import development.kit.time.DateTimeManager
import java.time.OffsetTime

open class OpeningTime(
    private val open: OffsetTime,
    private val close: OffsetTime
)
{
    open val uniqueId: Long ? = null

    init
    {
        if (DateTimeManager.isStartTimeAfterOrEqualEndTime(this.open, this.close)) throw OpeningTimeException("Illegal opening time: open >= close")
    }
}

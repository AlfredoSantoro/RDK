package development.kit.openingtimes

import development.kit.exception.OpeningTimeException
import development.kit.time.DateTimeManager
import java.time.OffsetTime

open class OpeningTime(
    private var open: OffsetTime,
    private var close: OffsetTime
)

{
    init
    {
        if (!DateTimeManager.isAValidTime(this.open, this.close)) throw OpeningTimeException("Illegal opening time: open > close")
    }
}

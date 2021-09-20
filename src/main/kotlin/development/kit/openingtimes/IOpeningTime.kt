package development.kit.openingtimes

import java.time.DayOfWeek

interface IOpeningTime
{
    fun findByDayOfWeek(dayOfWeek: DayOfWeek): OpeningTime?
}
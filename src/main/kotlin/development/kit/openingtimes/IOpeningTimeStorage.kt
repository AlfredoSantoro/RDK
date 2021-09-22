package development.kit.openingtimes

import java.time.DayOfWeek

interface IOpeningTimeStorage
{
    fun findByDayOfWeek(dayOfWeek: DayOfWeek): OpeningTime?
}
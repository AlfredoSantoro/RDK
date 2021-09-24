import development.kit.openingtimes.OpeningTimeManager
import org.junit.Assert
import org.junit.Test
import java.time.DayOfWeek
import java.time.OffsetTime

class OpeningTimeManagerTest
{

    @Test
    fun `Should create a openingTimes`()
    {
        val open = OffsetTime.now()
        Assert.assertNotNull(OpeningTimeManager
            .createPeriodicOpeningTime(DayOfWeek.MONDAY, open, open.plusHours(1)))
    }

    @Test
    fun `Should update a openingTimes`()
    {
        val openingTime = OpeningTimeManager
            .createPeriodicOpeningTime(DayOfWeek.MONDAY, OffsetTime.now(), OffsetTime.now().plusHours(1))
        Assert.assertNotNull(openingTime)
        val newOpeningTime = OffsetTime.now().minusMinutes(10)
        val openingTimeUpdated =
            OpeningTimeManager
                .updatePeriodicOpeningTimes(openingTime, newOpeningTime, openingTime.closingTime)
        Assert.assertNotNull(openingTimeUpdated)
        Assert.assertEquals(openingTimeUpdated.openingTime, newOpeningTime)
    }
}
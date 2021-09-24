import development.kit.time.DateTimeManager
import org.junit.Assert
import org.junit.Test
import java.time.OffsetDateTime
import java.time.OffsetTime

class DateTimeManagerTest
{
    @Test
    fun `Should say that startDateTime is before endDateTime`()
    {
        val start = OffsetDateTime.now()
        val end = OffsetDateTime.now().plusMinutes(10)
        Assert.assertTrue(DateTimeManager.isStartDateTimeBeforeEndDateTime(start,end))
    }

    @Test
    fun `Should not say that startDateTime is before endDateTime`()
    {
        val start = OffsetDateTime.now()
        val end = OffsetDateTime.now().minusMinutes(10)
        Assert.assertFalse(DateTimeManager.isStartDateTimeBeforeEndDateTime(start,end))
    }

    @Test
    fun `Should not say that startDateTime is before endDateTime because it are equals`()
    {
        val time = OffsetDateTime.now()
        Assert.assertFalse(DateTimeManager.isStartDateTimeBeforeEndDateTime(time,time))
    }

    @Test
    fun `Should say that startTime is before endTime`()
    {
        val start = OffsetTime.now()
        val end = OffsetTime.now().plusHours(1)
        Assert.assertTrue(DateTimeManager.isStartTimeBeforeEndTime(start,end))
    }

    @Test
    fun `Should not say that startTime is before endTime`()
    {
        val start = OffsetTime.now()
        val end = OffsetTime.now().minusMinutes(1)
        Assert.assertFalse(DateTimeManager.isStartTimeBeforeEndTime(start,end))
    }
}
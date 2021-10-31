import development.kit.utils.OffsetDateTimeUtils
import org.junit.Assert
import org.junit.Test
import java.time.Duration
import java.time.OffsetDateTime

class OffsetDateTimeUtilsTest
{
    @Test
    fun `Should add minutes to time`()
    {
        val minutes = 10L
        val duration = Duration.ofMinutes(minutes)
        val now = OffsetDateTime.now()
        val timeUpdated = OffsetDateTimeUtils.addDurationToTime(now, duration)
        Assert.assertNotNull(timeUpdated)
        Assert.assertEquals(now.plusMinutes(minutes).minute, timeUpdated!!.minute)
    }

    @Test
    fun `Should say you that start is greater then end`()
    {
        Assert.assertTrue(OffsetDateTimeUtils.isStartGreaterThanEnd(OffsetDateTime.now(),
            OffsetDateTime.now().minusMinutes(1)))
    }

    @Test
    fun `Should say you that start is less then end`()
    {
        Assert.assertFalse(OffsetDateTimeUtils.isStartGreaterThanEnd(OffsetDateTime.now(),
            OffsetDateTime.now()))
    }
}
import development.kit.asset.Seat
import development.kit.openingtimes.OpeningTimeManager
import development.kit.reservation.ReservationManager
import development.kit.user.Account
import development.kit.user.AccountType
import org.junit.Assert
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.OffsetDateTime
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
                .updatePeriodicOpeningTimes(openingTime, newOpeningTime, openingTime.close)
        Assert.assertNotNull(openingTimeUpdated)
        Assert.assertEquals(openingTimeUpdated.open, newOpeningTime)
    }

    @Test
    fun `Should say that the new reservation is included in the opening times`()
    {
        val now = OffsetDateTime.now()
        val reservationStart = OffsetDateTime.of(now.toLocalDate(), LocalTime.of(9,30), now.offset)
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = ReservationManager.createSeatReservation(reservationStart,
            reservationStart.plusMinutes(10), seat, account)
        Assert.assertNotNull(reservation)
        val openingTime = OpeningTimeManager
            .createPeriodicOpeningTime(now.dayOfWeek, OffsetTime.of(LocalTime.of(9,0), now.offset),
                OffsetTime.of(LocalTime.of(19,0), now.offset))
        Assert.assertNotNull(openingTime)
        val result = OpeningTimeManager.reservationIsIncludedInTheOpeningTimes(openingTime, reservation.start, reservation.end)
        Assert.assertTrue(result)
    }

    @Test
    fun `Should say that the new reservation is not included in the opening times`()
    {
        val now = OffsetDateTime.now()
        val reservationStart = OffsetDateTime.of(now.toLocalDate(), LocalTime.of(23,0), now.offset)
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = ReservationManager.createSeatReservation(reservationStart,
            reservationStart.plusMinutes(10), seat, account)
        Assert.assertNotNull(reservation)
        val openingTime = OpeningTimeManager
            .createPeriodicOpeningTime(now.dayOfWeek, OffsetTime.of(LocalTime.of(9,0), now.offset),
                OffsetTime.of(LocalTime.of(19,0), now.offset))
        Assert.assertNotNull(openingTime)
        val result = OpeningTimeManager.reservationIsIncludedInTheOpeningTimes(openingTime, reservation.start, reservation.end)
        Assert.assertFalse(result)
    }
}
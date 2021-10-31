import development.kit.asset.Asset
import development.kit.asset.Seat
import development.kit.openingtimes.OpeningTimeManager
import development.kit.reservation.ReservationManager
import development.kit.reservation.ReservationRules
import development.kit.user.Account
import development.kit.user.AccountType
import org.junit.Assert
import org.junit.Test
import java.time.*

class OpeningTimeManagerTest
{
    private val reservationManager = ReservationManager(object : ReservationRules {
        override fun isOverlappingUserReservations(
            account: Account,
            startReservation: OffsetDateTime,
            endReservation: OffsetDateTime
        ): Boolean { return false }

        override fun isAssetAvailable(
            asset: Asset,
            startReservation: OffsetDateTime,
            endReservation: OffsetDateTime
        ): Boolean { return true }
    })

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
        val reservation = this.reservationManager.createReservation(account, reservationStart,
            Duration.ofMinutes(10), -1, seat)
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
        val reservation = this.reservationManager.createReservation(account, reservationStart,
            Duration.ofMinutes(10), -1, seat)
        Assert.assertNotNull(reservation)
        val openingTime = OpeningTimeManager
            .createPeriodicOpeningTime(now.dayOfWeek, OffsetTime.of(LocalTime.of(9,0), now.offset),
                OffsetTime.of(LocalTime.of(19,0), now.offset))
        Assert.assertNotNull(openingTime)
        val result = OpeningTimeManager.reservationIsIncludedInTheOpeningTimes(openingTime, reservation.start, reservation.end)
        Assert.assertFalse(result)
    }
}
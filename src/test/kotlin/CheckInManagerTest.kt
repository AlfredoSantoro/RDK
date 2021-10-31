import development.kit.asset.Asset
import development.kit.asset.Seat
import development.kit.checkin.CheckIn
import development.kit.checkin.CheckInFactory
import development.kit.checkin.CheckInManager
import development.kit.checkin.CheckInRules
import development.kit.reservation.ReservationManager
import development.kit.reservation.ReservationRules
import development.kit.user.Account
import development.kit.user.AccountType
import org.junit.Assert
import org.junit.Test
import java.time.Duration
import java.time.OffsetDateTime

class CheckInManagerTest
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

    private val checkInManagerValidTrue = CheckInManager(object : CheckInRules{
        override fun isInTime(checkIn: CheckIn): Boolean { return true }
    })

    private val checkInManagerValidFalse = CheckInManager(object : CheckInRules{
        override fun isInTime(checkIn: CheckIn): Boolean { return false }
    })

    @Test
    fun `Should validate a check-in`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account,
            OffsetDateTime.now(),
            Duration.ofMinutes(10), seat)
        Assert.assertNotNull(reservation)
        val checkIn = CheckInFactory.createDefaultCheckInNow(reservation, account, -1)
        Assert.assertNotNull(checkIn)
        Assert.assertTrue(this.checkInManagerValidTrue.checkInValidation(checkIn).isValid)
    }

    @Test
    fun `Should tell you that the check-in is not valid`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account,
            OffsetDateTime.now(),
            Duration.ofMinutes(10), seat)
        Assert.assertNotNull(reservation)
        val checkIn = CheckInFactory.createDefaultCheckInNow(reservation, account, -1)
        Assert.assertNotNull(checkIn)
        Assert.assertFalse(this.checkInManagerValidFalse.isValidCheckIn(checkIn))
    }
}
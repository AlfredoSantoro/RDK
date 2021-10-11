import development.kit.asset.Seat
import development.kit.checkin.CheckInManager
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
        override fun checkOverlappingUserReservations(
            userId: Long,
            startReservation: OffsetDateTime,
            endReservation: OffsetDateTime
        ) {}

        override fun checkAssetAvailability(
            assetId: Long,
            startReservation: OffsetDateTime,
            endReservation: OffsetDateTime
        ) {}
    })

    @Test
    fun `Should create a check-in`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account,
            OffsetDateTime.now(),
            Duration.ofMinutes(10), seat)
        Assert.assertNotNull(reservation)
        Assert.assertNotNull(CheckInManager.makeCheckInNow(reservation, account))
    }

    @Test
    fun `Should tell you that the check-in on time`()
    {
        val checkInFrequency = Duration.ofMinutes(15)
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account,
            OffsetDateTime.now(),
            Duration.ofMinutes(10), seat)
        Assert.assertNotNull(reservation)
        val checkIn = CheckInManager.makeCheckInNow(reservation, account)
        Assert.assertNotNull(checkIn)
        Assert.assertTrue(CheckInManager.wasCheckInDoneInFrequency(reservation.start, checkInFrequency, checkIn))
    }

    @Test
    fun `Should tell you that the check-in was not done in time`()
    {
        val checkInFrequency = Duration.ofMinutes(15)
        val startReservation = OffsetDateTime.now().minus(checkInFrequency)
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account, startReservation,
            Duration.ofHours(1), seat)
        Assert.assertNotNull(reservation)
        val checkIn = CheckInManager.makeCheckInNow(reservation, account)
        Assert.assertNotNull(checkIn)
        Assert.assertFalse(CheckInManager.wasCheckInDoneInFrequency(reservation.start, checkInFrequency, checkIn))
    }

    @Test
    fun `Should tell you that you didn't check in on time with respect to the last one made`()
    {
        val checkInFrequency = Duration.ofMinutes(15)
        val startReservation = OffsetDateTime.now()
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account, startReservation,
            Duration.ofHours(1), seat)
        Assert.assertNotNull(reservation)
        val initialCheckIn = CheckInManager.makeCheckInNow(reservation, account)
        Assert.assertTrue(CheckInManager.wasCheckInDoneInFrequency(reservation.start, checkInFrequency, initialCheckIn))
        val secondCheckIn = CheckInManager.createCheckIn(reservation, initialCheckIn.time.plus(checkInFrequency), account)
        Assert.assertFalse(CheckInManager.wasCheckInDoneInFrequency(initialCheckIn.time, checkInFrequency, secondCheckIn))
    }

    @Test
    fun `Should tell you that the check-in is still in time to be done`()
    {
        val checkInFrequency = Duration.ofMinutes(15)
        val startReservation = OffsetDateTime.now()
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account, startReservation,
            Duration.ofHours(1), seat)
        Assert.assertNotNull(reservation)
        val initialCheckIn = CheckInManager.makeCheckInNow(reservation, account)
        Assert.assertTrue(CheckInManager.isFrequencyIntervalInProgress(initialCheckIn.time, checkInFrequency))
    }

    @Test
    fun `Should tell you that the check-in is not yet in time to be done`()
    {
        val checkInFrequency = Duration.ofMinutes(15)
        val startReservation = OffsetDateTime.now().minus(checkInFrequency)
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account, startReservation,
            Duration.ofHours(1), seat)
        Assert.assertNotNull(reservation)
        Assert.assertFalse(CheckInManager.isFrequencyIntervalInProgress(startReservation, checkInFrequency))
    }
}
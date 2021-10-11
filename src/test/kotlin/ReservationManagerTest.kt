import development.kit.asset.Seat
import development.kit.reservation.ReservationManager
import development.kit.reservation.ReservationRules
import development.kit.user.Account
import development.kit.user.AccountType
import org.junit.Assert
import org.junit.Test
import java.time.Duration
import java.time.OffsetDateTime

class ReservationManagerTest
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
    fun `Should create a simple seat reservation`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account, OffsetDateTime.now(),
            Duration.ofMinutes(10), seat)
        Assert.assertNotNull(reservation)
    }

    @Test
    fun `Should create a seat reservation with an amount to be added to start`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account, OffsetDateTime.now(),
            Duration.ofHours(1), seat)
        Assert.assertNotNull(reservation)
        Assert.assertEquals(reservation.end, reservation.start.plus(Duration.ofHours(1)))
    }

    @Test
    fun `Should create a seat reservation with an amount to be added to start and a representationUnit`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account, OffsetDateTime.now(),
            Duration.ofHours(1), seat)
        Assert.assertNotNull(reservation)
        Assert.assertEquals(reservation.end, reservation.start.plus(Duration.ofHours(1)))
    }

    @Test
    fun `Should create a simple seatReservation`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = this.reservationManager.createReservation(account, OffsetDateTime.now(),
            Duration.ofHours(1), seat)
        Assert.assertNotNull(reservation)
    }

    @Test
    fun `Should create a seatReservation with name`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation =this.reservationManager.createReservation(account, OffsetDateTime.now(),
            Duration.ofHours(10), seat)
        Assert.assertNotNull(reservation)
    }

    @Test
    fun `Should pause a seatReservation`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservationPause = this.reservationManager.createReservationPause(account, OffsetDateTime.now(),
            Duration.ofHours(1), seat)
        Assert.assertNotNull(reservationPause)
        val pauseReservation = this.reservationManager.pauseReservation(reservationPause)
        Assert.assertTrue(pauseReservation.inPause)
    }
}
import development.kit.asset.Seat
import development.kit.reservation.ReservationManager
import development.kit.user.Account
import development.kit.user.AccountType
import org.junit.Assert
import org.junit.Test
import java.time.Duration
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

class ReservationManagerTest
{

    @Test
    fun `Should create a simple base reservation`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = ReservationManager.createBaseReservation(OffsetDateTime.now(),
            OffsetDateTime.now().plusMinutes(10), seat, account)
        Assert.assertNotNull(reservation)
    }

    @Test
    fun `Should create a base reservation with an amount to be added to start`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = ReservationManager.createBaseReservation(OffsetDateTime.now(),
            Duration.ofHours(1), seat, account)
        Assert.assertNotNull(reservation)
        Assert.assertEquals(reservation.end, reservation.start.plus(Duration.ofHours(1)))
    }

    @Test
    fun `Should create a base reservation with an amount to be added to start and a representationUnit`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = ReservationManager.createBaseReservation(OffsetDateTime.now(),
            1, ChronoUnit.HOURS, seat, account)
        Assert.assertNotNull(reservation)
        Assert.assertEquals(reservation.end, reservation.start.plus(Duration.ofHours(1)))
    }

    @Test
    fun `Should create a simple seatReservation`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = ReservationManager.createSeatReservation(OffsetDateTime.now(),
            OffsetDateTime.now().plusHours(1), seat, account)
        Assert.assertNotNull(reservation)
    }

    @Test
    fun `Should create a seatReservation with name`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = ReservationManager.createSeatReservation("testreservation", OffsetDateTime.now(),
            OffsetDateTime.now().plusHours(1), seat, account)
        Assert.assertNotNull(reservation)
    }

    @Test
    fun `Should update a seatReservation`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = ReservationManager.createSeatReservation("testreservation", OffsetDateTime.now(),
            OffsetDateTime.now().plusHours(1), seat, account)
        Assert.assertNotNull(reservation)
        val newStart = OffsetDateTime.now().minusMinutes(30)
        val newEnd = OffsetDateTime.now()
        val newName = "newname"
        val updatedReservation = ReservationManager.updateSeatReservation(reservation, reservation.seat, newName,
            newStart, newEnd)
        Assert.assertNotNull(updatedReservation)
        Assert.assertEquals(updatedReservation.name, newName)
        Assert.assertEquals(updatedReservation.reservationStart, newStart)
        Assert.assertEquals(updatedReservation.reservationEnd, newEnd)
    }

    @Test
    fun `Should pause a seatReservation`()
    {
        val seat = Seat("testSeat", true)
        val account = Account("testname", "testsurname",
            "testemail", "testusername", "testpass", AccountType.USER)
        val reservation = ReservationManager.createSeatReservation("testreservation", OffsetDateTime.now(),
            OffsetDateTime.now().plusHours(1), seat, account)
        Assert.assertNotNull(reservation)
        val pauseReservation = ReservationManager.pauseReservation(reservation)
        Assert.assertTrue(pauseReservation.inPause)
    }
}
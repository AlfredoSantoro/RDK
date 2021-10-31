import development.kit.asset.AssetManager
import development.kit.asset.AssetState
import development.kit.asset.InsertUpdateSeat
import development.kit.identifier.TagNFC
import development.kit.reservation.ReservationPause
import development.kit.user.Account
import development.kit.user.AccountType
import org.junit.Assert
import org.junit.Test
import java.time.OffsetDateTime

class AssetManagerTest
{
    @Test
    fun `Should create a simple asset`()
    {
        val insertUpdateSeat = InsertUpdateSeat("testseat", true, null)
        Assert.assertNotNull(AssetManager.createSeat(insertUpdateSeat))
    }

    @Test
    fun `Should create an asset with tagNFC`()
    {
        val insertUpdateSeat = InsertUpdateSeat("testseat", true, TagNFC("testname","xxx"))
        Assert.assertNotNull(AssetManager.createSeat(insertUpdateSeat))
    }

    @Test
    fun `Should update an asset`()
    {
        val insertUpdateSeat = InsertUpdateSeat("testseat", true, TagNFC("testname","xxx"))
        val seat = AssetManager.createSeat(insertUpdateSeat)
        val seatUpdated = AssetManager.updateSeat(InsertUpdateSeat("newname", false, seat.identifier), seat)
        Assert.assertNotNull(seatUpdated)
        Assert.assertEquals(seatUpdated.name, "newname")
        Assert.assertEquals(seatUpdated.canBeBooked, false)
    }

    @Test
    fun `Should say that the asset is available`()
    {
        val insertUpdateSeat = InsertUpdateSeat("testseat", true, TagNFC("testname","xxx"))
        val seat = AssetManager.createSeat(insertUpdateSeat)
        Assert.assertEquals(AssetManager.getCurrentSeatsState(seat, null), AssetState.FREE)
    }

    @Test
    fun `Should say that the asset is unavailable`()
    {
        val insertUpdateSeat = InsertUpdateSeat("testseat", false, TagNFC("testname","xxx"))
        val seat = AssetManager.createSeat(insertUpdateSeat)
        Assert.assertEquals(AssetManager.getCurrentSeatsState(seat, null), AssetState.UNAVAILABLE)
    }

    @Test
    fun `Should say that the asset paused`()
    {
        val user = Account("name-test", "surname-test", "email-test", "test", "test", AccountType.USER)
        val insertUpdateSeat = InsertUpdateSeat("testseat", true, TagNFC("testname","xxx"))
        val seat = AssetManager.createSeat(insertUpdateSeat)
        val seatReservation = ReservationPause(OffsetDateTime.now(), OffsetDateTime.now(), seat, user, true, -1)
        Assert.assertEquals(AssetManager.getCurrentSeatsState(seat, seatReservation), AssetState.PAUSED)
    }

    @Test
    fun `Should say that the asset occupied`()
    {
        val user = Account("name-test", "surname-test", "email-test", "test", "test", AccountType.USER)
        val insertUpdateSeat = InsertUpdateSeat("testseat", true, TagNFC("testname","xxx"))
        val seat = AssetManager.createSeat(insertUpdateSeat)
        val seatReservation = ReservationPause( OffsetDateTime.now(), OffsetDateTime.now(), seat, user, false, -1)
        Assert.assertEquals(AssetManager.getCurrentSeatsState(seat, seatReservation), AssetState.OCCUPIED)
    }
}
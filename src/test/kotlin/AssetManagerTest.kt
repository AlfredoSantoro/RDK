import development.kit.asset.AssetManager
import development.kit.asset.InsertUpdateSeat
import development.kit.identifier.TagNFC
import org.junit.Assert
import org.junit.Test

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
        AssetManager.getAssetState(seat, isSeatPaused = false, isSeatBookedForDate = false)
    }

    @Test
    fun `Should say that the asset is unavailable`()
    {
        val insertUpdateSeat = InsertUpdateSeat("testseat", false, TagNFC("testname","xxx"))
        val seat = AssetManager.createSeat(insertUpdateSeat)
        AssetManager.getAssetState(seat, isSeatPaused = false, isSeatBookedForDate = false)
    }

    @Test
    fun `Should say that the asset paused`()
    {
        val insertUpdateSeat = InsertUpdateSeat("testseat", true, TagNFC("testname","xxx"))
        val seat = AssetManager.createSeat(insertUpdateSeat)
        AssetManager.getAssetState(seat, isSeatPaused = true, isSeatBookedForDate = true)
    }

    @Test
    fun `Should say that the asset occupied`()
    {
        val insertUpdateSeat = InsertUpdateSeat("testseat", true, TagNFC("testname","xxx"))
        val seat = AssetManager.createSeat(insertUpdateSeat)
        AssetManager.getAssetState(seat, isSeatPaused = false, isSeatBookedForDate = true)
    }
}
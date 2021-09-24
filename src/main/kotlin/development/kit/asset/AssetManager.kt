package development.kit.asset

object AssetManager
{
    fun createSeat(insertUpdateSeat: InsertUpdateSeat): Seat
    {
        val newSeat = Seat(insertUpdateSeat.name, insertUpdateSeat.canBeBooked)
        newSeat.identifier = insertUpdateSeat.identifier
        return newSeat
    }

    fun updateSeat(insertUpdateSeat: InsertUpdateSeat, seatToUpdate: Seat): Seat
    {
        seatToUpdate.name = insertUpdateSeat.name
        seatToUpdate.canBeBooked = insertUpdateSeat.canBeBooked
        seatToUpdate.identifier = insertUpdateSeat.identifier
        return seatToUpdate
    }

    fun getAssetState(asset: Asset, isSeatPaused:Boolean, isSeatBookedForDate: Boolean): AssetState
    {
        return when {
            !asset.canBeBooked -> AssetState.UNAVAILABLE
            isSeatPaused -> AssetState.PAUSED
            isSeatBookedForDate -> AssetState.OCCUPIED
            else -> AssetState.FREE
        }
    }
}
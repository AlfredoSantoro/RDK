package development.kit.asset

/**
 * AssetManager è la classe che gestisce le logiche relative alla classe Asset e suoi discendenti
 */
object AssetManager
{
    /**
     * Crea un nuovo Seat
     * @param insertUpdateSeat i dati del nuovo Seat
     */
    fun createSeat(insertUpdateSeat: InsertUpdateSeat): Seat
    {
        val newSeat = Seat(insertUpdateSeat.name, insertUpdateSeat.canBeBooked)
        newSeat.identifier = insertUpdateSeat.identifier
        return newSeat
    }

    /**
     * Aggiorna un seat
     * @param insertUpdateSeat i nuovi dati del Seat
     * @param seatToUpdate il Seat da aggiornare
     */
    fun updateSeat(insertUpdateSeat: InsertUpdateSeat, seatToUpdate: Seat): Seat
    {
        seatToUpdate.name = insertUpdateSeat.name
        seatToUpdate.canBeBooked = insertUpdateSeat.canBeBooked
        seatToUpdate.identifier = insertUpdateSeat.identifier
        return seatToUpdate
    }

    /**
     * Ottiene lo stato di un posto
     * @param canBeBooked indica se il posto può essere prenotato
     * @param isSeatPaused indica se il posto è stato messo in pausa
     * @param isSeatBookedForDate indica se il posto è stato prenotato
     */
    fun getAssetState(canBeBooked: Boolean, isSeatPaused:Boolean, isSeatBookedForDate: Boolean): AssetState
    {
        return when {
            !canBeBooked -> AssetState.UNAVAILABLE
            isSeatPaused -> AssetState.PAUSED
            isSeatBookedForDate -> AssetState.OCCUPIED
            else -> AssetState.FREE
        }
    }
}
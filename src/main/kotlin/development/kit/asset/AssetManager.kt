package development.kit.asset

import development.kit.reservation.BaseReservation
import development.kit.reservation.ReservationPause

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
     * Calcola lo stato attuale di un Asset
     * @param asset l'asset per il quale si vuole calcolare lo stato
     * @param reservationOnGoing prenotazione in corso dell'asset
     */
    fun getCurrentAssetState(asset: Asset, reservationOnGoing: BaseReservation?): AssetState
    {
        return when {
            !asset.canBeBooked -> AssetState.UNAVAILABLE
            reservationOnGoing !== null -> AssetState.OCCUPIED
            else -> AssetState.FREE
        }
    }

    /**
     * Calcola lo stato attuale di una Seat
     * @param seat la seat per la quale si vuole calcolare lo stato
     * @param reservationOnGoing prenotazione in corso della seat
     */
    fun getCurrentSeatsState(seat: Seat, reservationOnGoing: ReservationPause?): AssetState
    {
        return when {
            reservationOnGoing !== null && reservationOnGoing.inPause -> AssetState.PAUSED
            else -> this.getCurrentAssetState(seat, reservationOnGoing)
        }
    }
}
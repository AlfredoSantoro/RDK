package development.kit.reservation

import development.kit.exception.IllegalReservationException
import development.kit.exception.ReservationConstraintsException
import development.kit.time.DateTimeManager

object ReservationValidation
{
    @Throws(Exception::class)
    fun validReservation(reservation: BaseReservation)
    {
        if ( !DateTimeManager.isStartDateTimeBeforeEndDateTime(reservation.start, reservation.end) )
        {
            throw IllegalReservationException("Illegal Reservation: start >= end")
        }
        if ( !reservation.asset.canBeBooked ) throw ReservationConstraintsException("Asset ${reservation.asset.name} cannot be reserved")
    }
}
package development.kit.reservation

import development.kit.asset.Seat
import development.kit.exception.IllegalReservationException
import development.kit.time.DateTimeManager
import development.kit.user.Account
import java.time.Duration
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

object ReservationManager
{

    fun createSeatReservation(name: String, start: OffsetDateTime, amountToBeAddedToStart: Duration,
                              seat: Seat, account: Account): SeatsReservationWithPause
    {
        return SeatsReservationWithPause(name, start, this.computeEndReservation(start, amountToBeAddedToStart), seat, account)
    }

    fun createSeatReservation(name: String, start: OffsetDateTime,
                              amountToBeAddedToStart: Long,
                              representationUnit: ChronoUnit,
                              seat: Seat, account: Account): SeatsReservationWithPause
    {
        return SeatsReservationWithPause(name, start,
            this.computeEndReservation(start, amountToBeAddedToStart, representationUnit), seat, account)
    }

    fun createSeatReservation(start: OffsetDateTime, end: OffsetDateTime, seat: Seat, account: Account): SeatsReservationWithPause
    {
        return SeatsReservationWithPause("Reservation of the ${OffsetDateTime.now()}", start, end, seat, account)
    }

    fun createSeatReservation(name: String, start: OffsetDateTime, end: OffsetDateTime,
                              seat: Seat, account: Account): SeatsReservationWithPause
    {
        return SeatsReservationWithPause(name, start, end, seat, account)
    }

    fun updateSeatReservation(reservation: SeatsReservationWithPause,
                              newSeat: Seat,
                              newName: String,
                              newStart: OffsetDateTime,
                              newEnd: OffsetDateTime): SeatsReservationWithPause
    {
        reservation.name = newName
        reservation.seat = newSeat
        reservation.start = newStart
        reservation.end = newEnd
        return reservation
    }


    fun pauseReservation(reservation: SeatsReservationWithPause): SeatsReservationWithPause
    {
        reservation.inPause = true
        return reservation
    }

    fun computeEndReservation(start: OffsetDateTime, amountToBeAddedToStart: Duration): OffsetDateTime
    {
        val endComputed = start.plus(amountToBeAddedToStart)
        if ( !DateTimeManager.isStartDateTimeBeforeEndDateTime(start, endComputed) )
        {
            throw IllegalReservationException("Illegal Reservation: start >= end")
        }
        return endComputed
    }

    fun computeEndReservation(start:OffsetDateTime, amountToBeAddedToStart: Long,
                              representationUnit: ChronoUnit): OffsetDateTime
    {
        val endComputed = start.plus(amountToBeAddedToStart, representationUnit)
        if ( !DateTimeManager.isStartDateTimeBeforeEndDateTime(start, endComputed) )
        {
            throw IllegalReservationException("Illegal Reservation: start > end or startTime = endTime")
        }
        return endComputed
    }
}
package development.kit.reservation

import development.kit.asset.Asset
import development.kit.asset.Seat
import development.kit.exception.IllegalReservationException
import development.kit.time.DateTimeManager
import development.kit.user.Account
import java.time.Duration
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

object ReservationManager
{
    fun createBaseReservation(start: OffsetDateTime, end: OffsetDateTime, asset: Asset, account: Account): BaseReservation
    {
        return BaseReservation(start, end, asset, account)
    }

    fun createBaseReservation(start: OffsetDateTime, amountToBeAddedToStart: Duration, asset: Asset, account: Account): BaseReservation
    {
        return BaseReservation(start, this.computeEndReservation(start, amountToBeAddedToStart), asset, account)
    }

    fun createBaseReservation(start: OffsetDateTime, amountToBeAddedToStart: Long, representationUnit: ChronoUnit, asset: Asset, account: Account): BaseReservation
    {
        return BaseReservation(start, this.computeEndReservation(start, amountToBeAddedToStart, representationUnit), asset, account)
    }

    fun createSeatReservation(start: OffsetDateTime, end: OffsetDateTime, seat: Seat, account: Account): SeatsReservation
    {
        return SeatsReservation("Reservation of the ${OffsetDateTime.now()}", start, end, seat, account)
    }

    fun createSeatReservation(name: String, start: OffsetDateTime, end: OffsetDateTime, seat: Seat, account: Account): SeatsReservation
    {
        return SeatsReservation(name, start, end, seat, account)
    }

    fun updateSeatReservation(reservation: SeatsReservation,
                              newSeat: Seat,
                              newName: String,
                              newStart: OffsetDateTime,
                              newEnd: OffsetDateTime): SeatsReservation
    {
        reservation.name = newName
        reservation.seat = newSeat
        reservation.reservationStart = newStart
        reservation.reservationEnd = newEnd
        return reservation
    }


    fun pauseReservation(reservation: SeatsReservation): SeatsReservation
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

    fun computeEndReservation(start:OffsetDateTime, amountToBeAddedToStart: Long, representationUnit: ChronoUnit): OffsetDateTime
    {
        val endComputed = start.plus(amountToBeAddedToStart, representationUnit)
        if ( !DateTimeManager.isStartDateTimeBeforeEndDateTime(start, endComputed) )
        {
            throw IllegalReservationException("Illegal Reservation: start > end or startTime = endTime")
        }
        return endComputed
    }
}
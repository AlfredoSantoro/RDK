package development.kit.reservation

import development.kit.asset.Asset
import development.kit.asset.Seat
import development.kit.exception.IllegalReservationException
import development.kit.time.DateTimeManager
import development.kit.user.User
import java.time.Duration
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

data class ReservationManager(
    private val iReservation: IReservation
)
{
    fun updateSeatReservation(reservation: SeatsReservation,
                              newSeat: Seat,
                              newName: String,
                              newStart: OffsetDateTime,
                              newEnd: OffsetDateTime): SeatsReservation
    {
        reservation.name = newName
        reservation.seat = newSeat
        reservation.startBooking = newStart
        reservation.endBooking = newEnd
        return reservation
    }

    fun pauseReservation(reservation: SeatsReservation): SeatsReservation
    {
        reservation.inPause = true
        return reservation
    }

    fun createBaseReservation(id: Long, start: OffsetDateTime, end: OffsetDateTime, asset: Asset, user: User): BaseReservation
    {
        return BaseReservation(start, end, asset, user, id)
    }

    fun createBaseReservation(id: Long, start: OffsetDateTime, amountToBeAddedToStart: Duration, asset: Asset, user: User): BaseReservation
    {
        return BaseReservation(start, this.computeEndReservation(start, amountToBeAddedToStart), asset, user, id)
    }

    fun createBaseReservation(id: Long, start: OffsetDateTime, amountToBeAddedToStart: Long, representationUnit: ChronoUnit, asset: Asset, user: User): BaseReservation
    {
        return BaseReservation(start, this.computeEndReservation(start, amountToBeAddedToStart, representationUnit), asset, user, id)
    }

    fun computeEndReservation(start: OffsetDateTime, amountToBeAddedToStart: Duration): OffsetDateTime
    {
        val endComputed = start.plus(amountToBeAddedToStart)
        if ( !DateTimeManager.isAValidPeriod(start, endComputed) )
        {
            throw IllegalReservationException("Illegal Reservation: start > end or startTime = endTime")
        }
        return endComputed
    }

    fun computeEndReservation(start:OffsetDateTime, amountToBeAddedToStart: Long, representationUnit: ChronoUnit): OffsetDateTime
    {
        val endComputed = start.plus(amountToBeAddedToStart, representationUnit)
        if ( !DateTimeManager.isAValidPeriod(start, endComputed) )
        {
            throw IllegalReservationException("Illegal Reservation: start > end or startTime = endTime")
        }
        return endComputed
    }

    fun createSeatReservation(id: Long, start: OffsetDateTime, end: OffsetDateTime, seat: Seat, user: User): SeatsReservation
    {
        return SeatsReservation("Reservation of the ${OffsetDateTime.now()}", start, end, seat, user, id)
    }

    fun createSeatReservation(id: Long, name: String, start: OffsetDateTime, end: OffsetDateTime, seat: Seat, user: User): SeatsReservation
    {
        return SeatsReservation(name, start, end, seat, user, id)
    }

    fun areUserReservationsOverlaps(userId: Long, start: OffsetDateTime, end: OffsetDateTime,
                                              excludeReservationId: Long ?= null): Boolean
    {
        return this.iReservation.getUserReservationsOverlaps(start, end, userId, excludeReservationId).isNotEmpty()
    }

    fun areAssetReservationsOverlaps(assetID: Long, start: OffsetDateTime, end: OffsetDateTime,
                                               excludeReservationId: Long ?= null): Boolean
    {
        return this.iReservation.getAssetReservationsOverlaps(start, end, assetID, excludeReservationId).isNotEmpty()
    }
}
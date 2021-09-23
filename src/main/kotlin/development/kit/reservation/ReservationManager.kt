package development.kit.reservation

import development.kit.asset.Asset
import development.kit.asset.Seat
import development.kit.exception.IllegalReservationException
import development.kit.time.DateTimeManager
import development.kit.user.Account
import java.time.Duration
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

data class ReservationManager(
    private val iReservationStorage: IReservationStorage
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
        reservation.start = newStart
        reservation.end = newEnd
        return reservation
    }

    fun pauseReservation(reservation: SeatsReservation): SeatsReservation
    {
        reservation.inPause = true
        return reservation
    }

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

    fun createSeatReservation(start: OffsetDateTime, end: OffsetDateTime, seat: Seat, account: Account): SeatsReservation
    {
        return SeatsReservation("Reservation of the ${OffsetDateTime.now()}", start, end, seat, account)
    }

    fun createSeatReservation(name: String, start: OffsetDateTime, end: OffsetDateTime, seat: Seat, account: Account): SeatsReservation
    {
        return SeatsReservation(name, start, end, seat, account)
    }

    fun areUserReservationsOverlaps(userId: Long, start: OffsetDateTime, end: OffsetDateTime,
                                              excludeReservationId: Long ?= null): Boolean
    {
        return this.iReservationStorage.getUserReservationsOverlaps(start, end, userId, excludeReservationId).isNotEmpty()
    }

    fun areAssetReservationsOverlaps(assetID: Long, start: OffsetDateTime, end: OffsetDateTime,
                                               excludeReservationId: Long ?= null): Boolean
    {
        return this.iReservationStorage.getAssetReservationsOverlaps(start, end, assetID, excludeReservationId).isNotEmpty()
    }
}
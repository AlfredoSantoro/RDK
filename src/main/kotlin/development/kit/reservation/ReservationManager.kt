package development.kit.reservation

import development.kit.asset.Asset
import development.kit.exception.IllegalReservationException
import development.kit.time.DateTimeManager
import development.kit.user.Account
import java.time.Duration
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

class ReservationManager(
    private val reservationRules: ReservationRules
)
{
    fun createReservation(account: Account,
                          start: OffsetDateTime,
                          reservationDuration : Duration,
                          asset: Asset): BaseReservation
    {
        val endReservation = this.computeEndReservation(start, reservationDuration)
        this.reservationRules.checkOverlappingUserReservations(account.id, start, endReservation)
        this.reservationRules.checkAssetAvailability(asset.id, start, endReservation)
        return BaseReservation(start, endReservation, asset, account)
    }

    fun createReservationPause(account: Account,
                               start: OffsetDateTime,
                               reservationDuration : Duration,
                               asset: Asset): ReservationPause
    {
        val res = this.createReservation(account, start, reservationDuration, asset)
        return ReservationPause(res.start, res.end, res.asset, res.owner)
    }

    fun isReservationOnGoing(start: OffsetDateTime, end: OffsetDateTime): Boolean
    {
        val now = OffsetDateTime.now()
        return (now >= start && now < end)
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


    fun pauseReservation(reservation: ReservationPause): ReservationPause
    {
        reservation.inPause = true
        return reservation
    }
}
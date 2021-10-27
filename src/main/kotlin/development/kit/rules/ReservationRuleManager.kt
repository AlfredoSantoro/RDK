package development.kit.rules

import development.kit.asset.Asset
import development.kit.exception.IllegalCheckInException
import development.kit.exception.IllegalReservationException
import development.kit.exception.ReservationNotFoundException
import development.kit.exception.ReservationOverlapsException
import development.kit.identifier.Identifier
import development.kit.reservation.BaseReservation
import development.kit.reservation.ReservationManager
import development.kit.reservation.ReservationRules
import development.kit.user.Account
import development.kit.utils.StringManager

class ReservationRuleManager(
    private val reservationRules: ReservationRules
)
{
    @Throws(ReservationOverlapsException::class)
    fun checkOverlapUserReservations(account: Account, reservation: BaseReservation)
    {
       if ( this.reservationRules.isOverlappingUserReservations(account, reservation.start, reservation.end) )
       {
           throw ReservationOverlapsException("Reservation overlaps with another for user #${account.accountId}")
       }
    }

    @Throws(IllegalReservationException::class)
    fun checkAssetAvailable(asset: Asset, reservation: BaseReservation)
    {
        if ( !this.reservationRules.isAssetAvailable(asset, reservation.start, reservation.end) )
        {
            throw IllegalReservationException("Asset #${asset.name} not available")
        }
    }

    @Throws(ReservationNotFoundException::class)
    fun checkReservationPresence(reservation: BaseReservation?, id: Long)
    {
        if ( reservation == null )
        {
            throw ReservationNotFoundException("Reservation $id not found")
        }
    }

    @Throws(IllegalCheckInException::class)
    fun checkReservationOwner(username: String, reservation: BaseReservation)
    {
        if ( !StringManager.areEquals(username, reservation.owner.username) )
        {
            throw IllegalCheckInException("An account other then the reservation owner cannot check-in")
        }
    }

    @Throws(IllegalCheckInException::class)
    fun checkReservationNfcTag(tagNFCValueScanned: String, seatIdentifier: Identifier)
    {
        if ( !StringManager.areEquals(tagNFCValueScanned, seatIdentifier.value) )
        {
            throw IllegalCheckInException("NFC tag not corresponding to the one of the reserved seat")
        }
    }

    @Throws(IllegalCheckInException::class)
    fun checkReservationOngoing(reservation: BaseReservation)
    {
        val reservationManager = ReservationManager(this.reservationRules)

        if ( !reservationManager.isReservationOnGoing(reservation.start, reservation.end) )
        {
            throw IllegalCheckInException("Cannot check-in for a ongoing reservation")
        }
    }
}
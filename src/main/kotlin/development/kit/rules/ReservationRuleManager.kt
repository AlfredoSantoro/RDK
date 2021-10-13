package development.kit.rules

import development.kit.asset.Asset
import development.kit.reservation.BaseReservation
import development.kit.reservation.ReservationRules
import development.kit.user.Account

class ReservationRuleManager(
    private val reservationRules: ReservationRules
)
{
    fun isOverlappingUserReservations(account: Account, reservation: BaseReservation): Boolean
    {
       return this.reservationRules.isOverlappingUserReservations(account, reservation.start, reservation.end)
    }

    fun isAssetAvailable(asset: Asset, reservation: BaseReservation): Boolean
    {
        return this.reservationRules.isAssetAvailable(asset, reservation.start, reservation.end)
    }
}
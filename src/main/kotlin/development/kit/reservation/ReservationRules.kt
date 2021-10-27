package development.kit.reservation

import development.kit.asset.Asset
import development.kit.user.Account
import java.time.OffsetDateTime

interface ReservationRules
{
    fun isOverlappingUserReservations(account: Account, startReservation: OffsetDateTime, endReservation: OffsetDateTime): Boolean

    fun isAssetAvailable(asset: Asset, startReservation: OffsetDateTime, endReservation: OffsetDateTime): Boolean

    fun isThereAReservation(id: Long): BaseReservation?
}
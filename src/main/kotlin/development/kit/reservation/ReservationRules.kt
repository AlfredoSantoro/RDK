package development.kit.reservation

import java.time.OffsetDateTime

interface ReservationRules
{
    @Throws(Exception::class)
    fun checkOverlappingUserReservations(userId: Long, startReservation: OffsetDateTime, endReservation: OffsetDateTime)

    @Throws(Exception::class)
    fun checkAssetAvailability(assetId: Long, startReservation: OffsetDateTime, endReservation: OffsetDateTime)
}
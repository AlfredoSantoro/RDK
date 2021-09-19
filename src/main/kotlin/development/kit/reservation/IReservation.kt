package development.kit.reservation

import java.time.OffsetDateTime

interface IReservation
{
    fun getUserReservationsOverlaps(start: OffsetDateTime, end: OffsetDateTime, userId: Long,
                                    excludeReservationId: Long?): Int
    fun getAssetReservationsOverlaps(start: OffsetDateTime, end: OffsetDateTime,
                                     assetId: Long, excludeReservationId: Long?): Int
}
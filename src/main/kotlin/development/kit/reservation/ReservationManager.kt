package development.kit.reservation

import development.kit.exception.ReservationConstraintsException
import java.time.OffsetDateTime

data class ReservationManager(
    private val iReservation: IReservation
)
{
    private fun checkUserReservationsOverlaps(userId: Long, start: OffsetDateTime, end: OffsetDateTime,
                                              excludeReservationId: Long ?= null)
    {
        if ( this.iReservation.getUserReservationsOverlaps(start, end, userId, excludeReservationId) > 0 )
        {
            throw ReservationConstraintsException("user #$userId cannot have two or more reservations at the same time")
        }
    }

    private fun checkAssetReservationsOverlaps(assetID: Long, start: OffsetDateTime, end: OffsetDateTime,
                                               excludeReservationId: Long ?= null)
    {
        if ( this.iReservation.getAssetReservationsOverlaps(start, end, assetID, excludeReservationId) > 0 )
        {
            throw ReservationConstraintsException("asset #$assetID is reserved")
        }
    }
}
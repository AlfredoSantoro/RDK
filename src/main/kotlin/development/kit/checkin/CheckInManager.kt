package development.kit.checkin

import development.kit.reservation.BaseReservation
import java.time.Duration
import java.time.OffsetDateTime

object CheckInManager
{
    fun makeCheckInNow(baseReservation: BaseReservation): CheckIn
    {
        return CheckIn(baseReservation, OffsetDateTime.now())
    }

    fun wasCheckInDoneInFrequency(start: OffsetDateTime, checkInFrequency: Duration, newCheckIn: CheckIn): Boolean
    {
        return newCheckIn.time < start.plus(checkInFrequency)
    }

    fun isFrequencyIntervalInProgress(start: OffsetDateTime, frequency: Duration): Boolean
    {
        return start.plus(frequency) > OffsetDateTime.now()
    }
}
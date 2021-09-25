package development.kit.checkin

import development.kit.reservation.BaseReservation
import development.kit.user.Account
import java.time.Duration
import java.time.OffsetDateTime

object CheckInManager
{
    fun makeCheckInNow(baseReservation: BaseReservation, owner: Account): CheckIn
    {
        return CheckIn(baseReservation, OffsetDateTime.now(), owner)
    }

    fun createCheckIn(baseReservation: BaseReservation, time: OffsetDateTime, owner: Account): CheckIn
    {
        return CheckIn(baseReservation, time, owner)
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
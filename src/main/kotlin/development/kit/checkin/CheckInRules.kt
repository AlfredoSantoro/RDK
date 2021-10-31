package development.kit.checkin

interface CheckInRules
{
    fun isInTime(checkIn: CheckIn): Boolean
}
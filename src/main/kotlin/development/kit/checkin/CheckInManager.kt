package development.kit.checkin

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CheckInManager(
    private val checkInRules: CheckInRules
)
{
    private val logger: Logger = LoggerFactory.getLogger(CheckInManager::class.java)

    fun isValidCheckIn(checkIn: CheckIn): Boolean { return this.checkInRules.isInTime(checkIn) }

    fun checkInValidation(checkIn: CheckIn): CheckIn
    {
        checkIn.isValid = this.isValidCheckIn(checkIn)
        this.logger.info("### check-in #${checkIn.checkInId} is valid ${checkIn.isValid}")
        return checkIn
    }
}
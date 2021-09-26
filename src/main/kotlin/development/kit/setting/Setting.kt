package development.kit.setting

import java.time.temporal.ChronoUnit

class Setting(
    val name: String,
    var value: Int,
    var description: String,
    var representationUnit: ChronoUnit
)
{
    val uniqueId: Long ? = null
}
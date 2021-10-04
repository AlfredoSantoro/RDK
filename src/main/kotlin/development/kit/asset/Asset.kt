package development.kit.asset

open class Asset(
    var name: String,
    var canBeBooked: Boolean,
)
{
    constructor(): this(
        "",
        false
    )
    val uniqueId: Long ? = null
}
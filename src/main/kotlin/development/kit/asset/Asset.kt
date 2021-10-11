package development.kit.asset

open class Asset(
    var name: String,
    var canBeBooked: Boolean
)
{
    var id: Long = -1

    constructor(): this(
        "",
        false
    )
}
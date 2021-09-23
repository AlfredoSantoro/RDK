package development.kit.asset

abstract class Asset(
    open val name: String,
    open var canBeBooked: Boolean,
)
{
    open val uniqueId: Long ? = null
}
package development.kit.asset

abstract class Asset(
    var name: String,
    var canBeBooked: Boolean,
)
{
    val uniqueId: Long ? = null
}
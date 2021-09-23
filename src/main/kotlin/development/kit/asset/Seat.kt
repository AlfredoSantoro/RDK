package development.kit.asset

import development.kit.identifier.Identifier

data class Seat(
    override var name: String,
    override var canBeBooked: Boolean,
): Asset(name, canBeBooked)
{
    var identifier: Identifier ? = null
}

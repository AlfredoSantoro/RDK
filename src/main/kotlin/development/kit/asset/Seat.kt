package development.kit.asset

import development.kit.identifier.Identifier

class Seat(
    name: String,
    canBeBooked: Boolean,
): Asset(name, canBeBooked)
{
    var identifier: Identifier ? = null
}

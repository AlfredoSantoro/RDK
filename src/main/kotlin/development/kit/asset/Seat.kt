package development.kit.asset

import development.kit.identifier.Identifier

data class Seat(
    var seatName: String,
    var canBeBooked: Boolean,
): Asset(seatName)
{
    var identifier: Identifier ? = null
}

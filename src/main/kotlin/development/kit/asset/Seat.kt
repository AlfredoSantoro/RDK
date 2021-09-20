package development.kit.asset

import development.kit.identifier.Identifier

data class Seat(
    var seatName: String,
    var canBeBooked: Boolean,
    val seatId: Long
): Asset(seatName, seatId)
{
    var identifier: Identifier ? = null
}

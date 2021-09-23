package development.kit.asset

import development.kit.identifier.Identifier

data class InsertUpdateSeat(
    val name: String,
    val canBeBooked: Boolean,
    val identifier: Identifier?
)
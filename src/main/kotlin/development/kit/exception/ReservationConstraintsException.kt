package development.kit.exception

data class ReservationConstraintsException(val msg: String): RuntimeException(msg) {}
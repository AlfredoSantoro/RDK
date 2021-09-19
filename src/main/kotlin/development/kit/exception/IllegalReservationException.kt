package development.kit.exception

data class IllegalReservationException(val msg: String): RuntimeException(msg) {}
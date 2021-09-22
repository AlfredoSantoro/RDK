package development.kit.exception

data class IllegalAccountException(val msg: String): RuntimeException(msg) {}
package development.kit.exception

data class IllegalAuthorizationException(val msg: String): RuntimeException(msg) {}
package development.kit.exception

data class LoginException(val msg: String): RuntimeException(msg)
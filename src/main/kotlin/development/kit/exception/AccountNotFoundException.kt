package development.kit.exception

data class AccountNotFoundException(val msg: String): Exception(msg)